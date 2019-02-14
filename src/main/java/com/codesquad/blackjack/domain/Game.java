package com.codesquad.blackjack.domain;

import com.codesquad.blackjack.domain.card.Card;
import com.codesquad.blackjack.domain.card.Deck;
import com.codesquad.blackjack.domain.user.User;
import com.codesquad.blackjack.dto.CardsDto;

public class Game {
    private static final int BLACKJACK_NUMBER = 21;

    private User dealer = new User();
    private User player = new User();
    private Chip totalBet = Chip.of(0);

    public Game play(Deck deck, int bettingChip) {
        this.totalBet = Chip.of(bettingChip * 2);
        drawInitCards(deck);

        return null;
    }

    private void drawInitCards(Deck deck) {
        for (int i = 0; i < 2; i++) {
            dealer.receiveCard(deck.draw());
            player.receiveCard(deck.draw());
        }
    }
    
    public User endByBlackjack() {
        if(isTie()) {
            player.winPrize(totalBet.half());
            return null;
        }

        if(getWinner().equals(player)) {
            player.winPrize(totalBet.blackjack());
            return player;
        }

        return dealer;
    }

    public User end() {
        if(isTie()) {
            player.winPrize(totalBet.half());
            return null;
        }

        if(getWinner().equals(player)) {
            player.winPrize(totalBet);
            return player;
        }

        return dealer;
    }

    public User endByDealerBurst() {
        player.winPrize(totalBet);
        return player;
    }

    private User getWinner() {
        return dealer.getTotal() > player.getTotal() ? dealer : player;
    }

    private boolean isTie() {
        return dealer.getTotal() == player.getTotal();
    }

    public boolean isBlackjack() {
        return isBlackjackUser(dealer) || isBlackjackUser(player);
    }

    private boolean isBlackjackUser(User user) {
        return user.getTotal() == BLACKJACK_NUMBER;
    }


    public Card getDealerInitCard() {
        return dealer.getCardsDto().getFirst();
    }

    public CardsDto getPlayerCards() {
        return player.getCardsDto();
    }

    public CardsDto getDealerCards() {
        return dealer.getCardsDto();
    }

    public void initializeGame() {
        player.initializeCards();
        dealer.initializeCards();
    }

    public Card hit(Deck deck) {
        return player.receiveCard(deck.draw());
    }

    public void dealerTurn(Deck deck) {
        while(dealer.getTotal() < 17) {
            dealer.receiveCard(deck.draw());
        }
    }

    public boolean isPlayerBurst() {
        return isBurstUser(player);
    }

    public boolean isDealerBurst() {
        return isBurstUser(dealer);
    }

    private boolean isBurstUser(User user) {
        return user.getTotal() > BLACKJACK_NUMBER;
    }

    public boolean isPlayerBlackjack() {
        return isBlackjackUser(player);
    }
}
