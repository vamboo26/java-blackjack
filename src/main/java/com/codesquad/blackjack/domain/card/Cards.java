package com.codesquad.blackjack.domain.card;

import com.codesquad.blackjack.dto.CardsDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static com.codesquad.blackjack.domain.card.Card.TEN;

@Getter
public class Cards {

    public static final int DEALER_HIT_NUMBER = 17;
    public static final int BLACKJACK_NUMBER = 21;

    private List<Card> cards = new ArrayList<>();
    private int total;

    public void add(Card card) {
        cards.add(card);
        total = calculateTotal();
    }

    public int getTotal() {
        return total;
    }

    public int calculateTotal() {
        int sum = 0;

        for (Card card : cards) {
            sum += card.getNumber();
        }

        if(hasAce()) {
            return (sum + TEN > BLACKJACK_NUMBER) ? sum : sum + TEN;
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

    public boolean isDealerHit() {
        return this.calculateTotal() < DEALER_HIT_NUMBER;
    }

    public boolean isBlackjack() {
        return calculateTotal() == BLACKJACK_NUMBER;
    }

    public boolean isBurst() {
        return calculateTotal() > BLACKJACK_NUMBER;
    }

    public boolean isTie(Cards target) {
        return this.calculateTotal() == target.calculateTotal();
    }

    public boolean isBigger(Cards target) {
        return calculateTotal() > target.calculateTotal();
    }

    public CardsDto _toCardsDto() {
        return new CardsDto(cards, calculateTotal());
    }

}
