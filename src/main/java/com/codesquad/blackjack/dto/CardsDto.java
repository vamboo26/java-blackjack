package com.codesquad.blackjack.dto;

import com.codesquad.blackjack.domain.card.Card;

import java.util.List;

public class CardsDto {
    private List<Card> cards;

    public CardsDto(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
