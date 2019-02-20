package com.codesquad.blackjack.domain;

import com.codesquad.blackjack.domain.card.Card;
import com.codesquad.blackjack.domain.card.Deck;
import com.codesquad.blackjack.domain.user.User;
import com.codesquad.blackjack.dto.CardsDto;
import com.codesquad.blackjack.dto.UserDto;

public class Game {
    public static final int BLACKJACK_NUMBER = 21;
    public static final String DEALER_NAME = "dealer";
    public static final String TIE = "무승부";
    public static final int HIT_SELECTION = 1;
    public static final int STAND_SELECTION = 2;
    public static final int DOUBLE_SELECTION = 3;

    private User dealer = new User(DEALER_NAME);
    private User player;
    private Chip totalBet = new Chip(0);
    private boolean gameProgress = true;

    public Game(String playerName) {
        this.player = new User(playerName);
    }

    public void init(Deck deck, int bettingChip) {
        this.totalBet = new Chip(bettingChip * 2);
        player.betChip(bettingChip);
        drawInitCards(deck);
    }

    private void drawInitCards(Deck deck) {
        for (int i = 0; i < 2; i++) {
            dealer.receiveCard(deck.draw());
            player.receiveCard(deck.draw());
        }
    }

    public Object end(Chip prize) {
        if(player.isWinner(dealer)) {
            return endByPlayerWin(prize);
        }

        return dealer.isTie(player) ? endByTie() : dealer._toUserDto();
    }

    public Chip getBlackjackPrize() {
        return totalBet.blackjack();
    }

    public Chip getNormalPrize() {
        return totalBet;
    }

    private Object endByTie() {
        player.winPrize(totalBet.half());
        return TIE;
    }

    public UserDto endByPlayerWin(Chip prize) {
        player.winPrize(prize);
        return player._toUserDto();
    }

    public void initializeGame() {
        player = player.initialize();
        dealer = dealer.initialize();
        gameProgress = true;
    }

    public boolean hasPlayerEnoughChip(int bettingChip) {
        return player.checkChip(bettingChip);
    }




    //return을 gameDto로해서 바로 뷰단으로 전달할수있도록 생각해보자
    public void hit(Deck deck) {
        player.hit(deck);
    }

    public void dealerTurn(Deck deck) {
        dealer.dealerTurn(deck);
    }




    public boolean isBlackjack() {
        return dealer.isBlackjack() || player.isBlackjack();
    }

    public boolean isBurst() {
        return dealer.isBurst() || player.isBurst();
    }

    public boolean playerHasNoMoney() {
        return player.isBankruptcy();
    }









    public boolean isGameProcess() {
        return gameProgress;
    }

    public void stopGame() {
        this.gameProgress = false;
    }

    public CardsDto getPlayerCards() {
        return player.getCardsDto();
    }

    public CardsDto getDealerCards() {
        return dealer.getCardsDto();
    }

    public UserDto getPlayerDto() {
        return player._toUserDto();
    }

    public UserDto getDealerDto() {
        return dealer._toUserDto();
    }
}
