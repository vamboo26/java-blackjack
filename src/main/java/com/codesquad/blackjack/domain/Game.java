package com.codesquad.blackjack.domain;

import com.codesquad.blackjack.domain.card.Card;
import com.codesquad.blackjack.domain.card.Deck;
import com.codesquad.blackjack.domain.player.Dealer;
import com.codesquad.blackjack.domain.player.Gamer;
import com.codesquad.blackjack.dto.GameDto;
import com.codesquad.blackjack.dto.UserDto;

public class Game {
    public static final String TIE = "무승부";
    public static final int HIT_SELECTION = 1;
    public static final int STAND_SELECTION = 2;
    public static final int DOUBLE_SELECTION = 3;

    private Dealer dealer = new Dealer();
    private Gamer gamer;
    private Chip totalBet = new Chip(0);
    private boolean gameProgress = true;

    public Game(String playerName) {
        this.gamer = new Gamer(playerName);
    }

    public void init(Deck deck, int bettingChip) {
        this.totalBet = new Chip(bettingChip);
        this.gamer.betChip(bettingChip);
        drawInitCards(deck);
    }

    private void drawInitCards(Deck deck) {
        for (int i = 0; i < 2; i++) {
            dealer.receiveCard(deck.draw());
            gamer.receiveCard(deck.draw());
        }
    }

    public void initializeGame() {
        this.gamer = gamer.initialize();
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

        if(dealer.isWinner(gamer)) {
            return endByPlayerWin(prize);
        }

        return dealer.isTie(gamer) ? endByTie() : dealer._toUserDto();
    }

    private Object endByTie() {
        gamer.winPrize(totalBet);
        return TIE;
    }

    public UserDto endByPlayerWin(Chip prize) {
        gamer.winPrize(prize);
        return gamer._toUserDto();
    }

    public Chip getBlackjackPrize() {
        return totalBet.blackjack();
    }

    public Chip getNormalPrize() {
        return totalBet.twice();
    }

    public void hit(Card card) {
        gamer.receiveCard(card);
    }

    public void dealerTurn(Card card) {
        dealer.dealerTurn(card);
    }

    public boolean isBlackjack() {
        return dealer.isBlackjack() || gamer.isBlackjack();
    }

    public boolean isBurst() {
        return dealer.isBurst() || gamer.isBurst();
    }

    public boolean hasGamerEnoughChip(int bettingChip) {
        return gamer.checkChip(bettingChip);
    }

    public boolean hasGamerNoMoney() {
        return gamer.isBankruptcy();
    }

    public UserDto getPlayerDto() {
        return gamer._toUserDto();
    }

    public UserDto getDealerDto() {
        return dealer._toUserDto();
    }

    public GameDto _toGameDto() {
        return new GameDto(dealer._toUserDto(), gamer._toUserDto(), totalBet);
    }
}
