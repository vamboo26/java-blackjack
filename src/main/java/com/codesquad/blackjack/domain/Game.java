package com.codesquad.blackjack.domain;

import com.codesquad.blackjack.domain.card.Card;
import com.codesquad.blackjack.domain.card.Deck;
import com.codesquad.blackjack.domain.player.Dealer;
import com.codesquad.blackjack.domain.player.User;
import com.codesquad.blackjack.dto.GameDto;
import com.codesquad.blackjack.dto.PlayerDto;

public class Game {
    public static final String TIE = "무승부";
    public static final int HIT_SELECTION = 1;
    public static final int STAND_SELECTION = 2;
    public static final int DOUBLE_SELECTION = 3;

    private Dealer dealer = new Dealer();
    private User user;
    private Chip totalBet = new Chip(0);
    private boolean gameProgress = true;

    public Game(String playerName) {
        this.user = new User(playerName);
    }

    public void init(Deck deck, int bettingChip) {
        this.totalBet = new Chip(bettingChip);
        this.user.betChip(bettingChip);
        drawInitCards(deck);
    }

    private void drawInitCards(Deck deck) {
        for (int i = 0; i < 2; i++) {
            dealer.receiveCard(deck.draw());
            user.receiveCard(deck.draw());
        }
    }

    public void initializeGame() {
        this.user = user.initialize();
        this.dealer = dealer.initialize();
        this.gameProgress = true;
    }

    public boolean isGameProcess() {
        return this.gameProgress;
    }

    public void stopGame() {
        this.gameProgress = false;
    }

    public Object end(Chip prize) {
        stopGame();

        if(dealer.isWinner(user)) {
            return endByPlayerWin(prize);
        }

        return dealer.isTie(user) ? endByTie() : dealer._toUserDto();
    }

    private Object endByTie() {
        user.winPrize(totalBet);
        return TIE;
    }

    public PlayerDto endByPlayerWin(Chip prize) {
        user.winPrize(prize);
        return user._toUserDto();
    }

    public Chip getBlackjackPrize() {
        return totalBet.blackjack();
    }

    public Chip getNormalPrize() {
        return totalBet.twice();
    }

    public void hit(Card card) {
        user.receiveCard(card);
    }

    public void dealerTurn(Card card) {
        dealer.dealerTurn(card);
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

    public PlayerDto getPlayerDto() {
        return user._toUserDto();
    }

    public PlayerDto getDealerDto() {
        return dealer._toUserDto();
    }

    public GameDto _toGameDto() {
        return new GameDto(dealer._toUserDto(), user._toUserDto(), totalBet);
    }
}
