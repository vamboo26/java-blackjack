package com.codesquad.blackjack.domain;

import com.codesquad.blackjack.domain.card.Deck;
import com.codesquad.blackjack.domain.player.Dealer;
import com.codesquad.blackjack.domain.player.User;
import com.codesquad.blackjack.dto.GameDto;
import lombok.Getter;

import java.util.Arrays;

@Getter
public class Game {

    private long id;
    //TODO dealer, user Pair 객체로 분리 고민
    private Dealer dealer = new Dealer();
    private User user;
    private Deck deck;
    private Chip totalBet;

    public Game(User loginUser) {
        this.user = loginUser;
    }

    public void initializeGame() {
        user.initialize();
        dealer.initialize();
        deck = Deck.auto();
        totalBet = new Chip(0);
    }

    public void init(int bettingChip) {
        //TODO exception 정리필요
        if (hasGamerNoMoney()) {
            throw new IllegalArgumentException("칩이 없어요");
        }

        totalBet = new Chip(bettingChip);
        user.betChip(bettingChip);
        drawInitCards(this.deck);
    }

    private void drawInitCards(Deck deck) {
        dealer.receiveCard(deck.draw());
        dealer.receiveCard(deck.draw());
        user.receiveCard(deck.draw());
        user.receiveCard(deck.draw());
    }

    //TODO 리턴타입 고민
    public String finishGame(GameStatus status) {
        return Arrays.stream(GameStatus.values())
                .filter(value -> value.equals(status))
                .findAny()
                .orElseThrow(IllegalAccessError::new)
                .finish(this);
    }

    public void hit() {
        user.receiveCard(this.deck.draw());
    }

    public void dealerTurn() {
        while (dealer.dealerTurn()) dealer.receiveCard(this.deck.draw());
    }

    public boolean isBlackjack() {
        return dealer.isBlackjack() || user.isBlackjack();
    }

    public boolean isBurst() {
        return dealer.isBurst() || user.isBurst();
    }

    public boolean hasGamerEnoughChip(int bettingChip) {
        return user.checkChip(bettingChip);
    }

    public boolean hasGamerNoMoney() {
        return user.isBankruptcy();
    }

    public void setId(long id) {
        this.id = id;
    }

    public void raiseDouble() {
        this.user.betChip(totalBet.getAmount());
        this.totalBet = totalBet.twice();
    }

    public GameDto _toGameDto() {
        return new GameDto(dealer, user, totalBet);
    }

    public GameDto _toGameDto(GameStatus status, String winner) {
        return new GameDto(dealer, user, totalBet, status, winner);
    }

    public enum GameStatus {
        //TODO 상수 하드코딩 처리, 혹은 리턴을 객체로 처리

        BLACKJACK {
            @Override
            String finish(Game game) {
                if (game.dealer.isTie(game.user)) {
                    return "TIE";
                }

                if (!game.dealer.isWinner(game.user)) {
                    game.user.winPrize(game.totalBet.blackjack());
                    return "USER";
                }

                return "DEALER";
            }
        },
        BURST {
            @Override
            String finish(Game game) {
                if (game.user.isBurst()) {
                    return "DEALER";
                }

                game.user.winPrize(game.totalBet.twice());
                return "USER";
            }
        },
        NORMAL {
            @Override
            String finish(Game game) {
                if (!game.dealer.isWinner(game.user)) {
                    game.user.winPrize(game.totalBet.twice());
                    return "USER";
                }

                if (!game.dealer.isTie(game.user)) {
                    return "DEALER";
                }

                return "TIE";
            }
        };

        abstract String finish(Game game);
    }

}