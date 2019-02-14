package com.codesquad.blackjack.dto;

import com.codesquad.blackjack.domain.card.Card;

import java.util.List;

public class CardsDto {
    private List<Card> cards;
    private int total;

    public CardsDto(List<Card> cards, int total) {
        this.cards = cards;
        this.total = total;
    }

    public List<Card> getCards() {
        return cards;
    }

    public Card getFirst() {
        return cards.get(0);
    }

    public int getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "CardsDto{" +
                "cards=" + cards +
                '}';
    }
}
