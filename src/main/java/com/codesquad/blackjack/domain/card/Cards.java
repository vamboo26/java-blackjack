package com.codesquad.blackjack.domain.card;

import com.codesquad.blackjack.domain.user.User;
import com.codesquad.blackjack.dto.CardsDto;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static com.codesquad.blackjack.domain.Game.BLACKJACK_NUMBER;

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
            return (sum + 10 > BLACKJACK_NUMBER) ? sum : sum + 10;
        }

        return sum;
    }

    boolean hasAce() {
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

    public boolean isTie(Cards target) {
        return this.calculateTotal() == target.calculateTotal();
    }

    public boolean isBurst() {
        return calculateTotal() > BLACKJACK_NUMBER;
    }

    public boolean isBlackjack() {
        return calculateTotal() == BLACKJACK_NUMBER;
    }

    public boolean isBigger(Cards target) {
        return calculateTotal() > target.calculateTotal();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cards cards1 = (Cards) o;
        return Objects.equals(cards, cards1.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }
}
