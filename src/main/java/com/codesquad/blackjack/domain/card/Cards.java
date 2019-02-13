package com.codesquad.blackjack.domain.card;

import com.codesquad.blackjack.dto.CardsDto;

import java.util.LinkedList;
import java.util.List;

public class Cards {
    private List<Card> cards = new LinkedList<>();

    public void add(Card card) {
        cards.add(card);
    }

    public CardsDto _toCardsDto() {
        return new CardsDto(cards);
    }
}
