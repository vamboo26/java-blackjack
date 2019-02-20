package com.codesquad.blackjack.domain;

import com.codesquad.blackjack.domain.card.Card;
import com.codesquad.blackjack.domain.card.Deck;
import com.codesquad.blackjack.domain.user.User;
import com.codesquad.blackjack.dto.GameDto;
import com.codesquad.blackjack.dto.UserDto;

public class Game {
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
        this.totalBet = new Chip(bettingChip);
        this.player.betChip(bettingChip);
        drawInitCards(deck);
    }

    private void drawInitCards(Deck deck) {
        for (int i = 0; i < 2; i++) {
            dealer.receiveCard(deck.draw());
            player.receiveCard(deck.draw());
        }
    }

    public void initializeGame() {
        this.player = player.initialize();
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

        if(player.isWinner(dealer)) {
            return endByPlayerWin(prize);
        }

        return dealer.isTie(player) ? endByTie() : dealer._toUserDto();
    }

    private Object endByTie() {
        player.winPrize(totalBet);
        return TIE;
    }

    public UserDto endByPlayerWin(Chip prize) {
        player.winPrize(prize);
        return player._toUserDto();
    }

    public Chip getBlackjackPrize() {
        return totalBet.blackjack();
    }

    public Chip getNormalPrize() {
        return totalBet.twice();
    }

    //return을 gameDto로해서 바로 뷰단으로 전달할수있도록 생각해보자
    public void hit(Card card) {
        player.receiveCard(card);
    }
    public void dealerTurn(Card card) {
        dealer.dealerTurn(card);
    }

    public boolean isBlackjack() {
        return dealer.isBlackjack() || player.isBlackjack();
    }

    public boolean isBurst() {
        return dealer.isBurst() || player.isBurst();
    }

    public boolean hasPlayerEnoughChip(int bettingChip) {
        return player.checkChip(bettingChip);
    }

    public boolean playerHasNoMoney() {
        return player.isBankruptcy();
    }

    public UserDto getPlayerDto() {
        return player._toUserDto();
    }

    public UserDto getDealerDto() {
        return dealer._toUserDto();
    }

    public GameDto _toGameDto() {
        return new GameDto(dealer._toUserDto(), player._toUserDto(), totalBet);
    }
}
