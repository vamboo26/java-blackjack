package com.codesquad.blackjack.domain.card;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static com.codesquad.blackjack.domain.card.Card.TEN;

@Getter
public class Cards {

    public static final int DEALER_HIT_NUMBER = 17;
    public static final int BLACKJACK_NUMBER = 21;

    private List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        cards.add(card);
    }

    //TODO
    // ACE 포함 시, 11로 계산해서 버스트가 아니면 무조건 11로 계산
    // 이게 최선?
    int getTotal() {
        int sum = cards.stream()
                .mapToInt(Card::getNumber)
                .sum();

        if(hasAce()) {
            return (sum + TEN > BLACKJACK_NUMBER) ? sum : sum + TEN;
        }

        return sum;
    }

    boolean hasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    public boolean isDealerHit() {
        return this.getTotal() < DEALER_HIT_NUMBER;
    }

    public boolean isBlackjack() {
        return getTotal() == BLACKJACK_NUMBER;
    }

    public boolean isBurst() {
        return getTotal() > BLACKJACK_NUMBER;
    }

    public boolean isTie(Cards target) {
        return this.getTotal() == target.getTotal();
    }

    public boolean isBigger(Cards target) {
        return getTotal() > target.getTotal();
    }

}
