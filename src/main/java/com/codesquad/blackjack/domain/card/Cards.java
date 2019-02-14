package com.codesquad.blackjack.domain.card;

import com.codesquad.blackjack.dto.CardsDto;

import java.util.LinkedList;
import java.util.List;

public class Cards {
    private List<Card> cards = new LinkedList<>();

    public void add(Card card) {
        cards.add(card);
    }

    //TODO : 리팩토링 필요
    public int calculateTotal() {
        int sum = 0;

        for (Card card : cards) {
            sum += card.getNumber();
        }

        if(hasAce()) {
            return (sum + 10 > 21) ? sum : sum + 10;
        }

        return sum;
    }

    private boolean hasAce() {
        for (Card card : cards) {
            if(card.isAce()) {
                return true;
            }
        }

        return false;
    }

    public CardsDto _toCardsDto() {
        return new CardsDto(cards, calculateTotal());
    }
}
