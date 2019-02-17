package com.codesquad.blackjack.domain;

import com.codesquad.blackjack.domain.card.Card;
import com.codesquad.blackjack.domain.card.Deck;
import com.codesquad.blackjack.domain.user.User;
import com.codesquad.blackjack.dto.CardsDto;

public class Game {
    public static final String DEALER_NAME = "dealer";
    public static final String TIE = "무승부";

    private User dealer = new User(DEALER_NAME);
    private User player;
    private Chip totalBet = Chip.of(0);
    private boolean gameProgress = true;

    public Game(String playerName) {
        this.player = new User(playerName);
    }

    public void init(Deck deck, int bettingChip) {
        this.totalBet = Chip.of(bettingChip * 2);
        player.betChip(bettingChip);
        drawInitCards(deck);
    }

    private void drawInitCards(Deck deck) {
        for (int i = 0; i < 2; i++) {
            dealer.receiveCard(deck.draw());
            player.receiveCard(deck.draw());
        }
    }

    public String end(Chip prize) {
        if(player.getTotal() > dealer.getTotal()) {
            return endByPlayerWin(prize);
        }

        return Rule.isTie(dealer, player) ? endByTie() : dealer._toUserDto().getName();
    }

    public Chip getBlackjackPrize() {
        return totalBet.blackjack();
    }

    public Chip getNormalPrize() {
        return totalBet;
    }

    private String endByTie() {
        player.winPrize(totalBet.half());
        return TIE;
    }

    public String endByPlayerWin(Chip prize) {
        player.winPrize(prize);
        return player._toUserDto().getName();
    }

    public void initializeGame() {
        player.initializeCards();
        dealer.initializeCards();
        gameProgress = true;
    }

    public boolean hasPlayerEnoughChip(int bettingChip) {
        return player.checkChip(bettingChip);
    }

    public Card hit(Deck deck) {
        return player.receiveCard(deck.draw());
    }

    public void dealerTurn(Deck deck) {
        while(dealer.getTotal() < 17) {
            dealer.receiveCard(deck.draw());
        }
    }

    public boolean isBlackjack() {
        return Rule.isBlackjackUser(dealer) || Rule.isBlackjackUser(player);
    }

    public boolean isPlayerBlackjack() {
        return Rule.isBlackjackUser(player);
    }

    public boolean isPlayerBurst() {
        return Rule.isBurstUser(player);
    }

    public boolean isDealerBurst() {
        return Rule.isBurstUser(dealer);
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

    public Object getPlayer() {
        return player;
    }

    public Object getDealer() {
        return dealer;
    }
}
