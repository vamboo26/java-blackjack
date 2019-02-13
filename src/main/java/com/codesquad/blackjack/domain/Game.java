package com.codesquad.blackjack.domain;

import com.codesquad.blackjack.domain.card.Card;
import com.codesquad.blackjack.domain.card.Deck;
import com.codesquad.blackjack.domain.user.User;

import java.util.List;

public class Game {
    private User dealer = new User();
    private User player = new User();
    private Chip totalBet = Chip.of(0);

    public Game play(Deck deck, int bettingChip) {

        this.totalBet = Chip.of(bettingChip);

        for (int i = 0; i < 2; i++) {
            dealer.receiveCard(deck.draw());
            player.receiveCard(deck.draw());
        }

        return null;
    }

    public Card getDealerInitCard() {
        return dealer.getCardsDto().getCards().get(0);
    }

    public List<Card> getPlayerCards() {
        return player.getCardsDto().getCards();
    }
}
