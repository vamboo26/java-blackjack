package com.codesquad.blackjack.domain.card;

import com.codesquad.blackjack.domain.Suit;
import com.google.common.base.MoreObjects;
import lombok.Getter;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;

@Getter
public class Card {

    public static final int ONE = 1;
    public static final int TEN = 10;
    public static final int ELEVEN = 11;
    public static final int TWELVE = 12;
    public static final int THIRTEEN = 13;

    private final int number;
    private final String name;
    private final Suit suit;

    Card(int number, Suit suit) {
        checkArgument(1 <= number && number <= 13,
                "card number must be between 1 and 13");

        this.number = numberToValidNumber(number);
        this.name = numberToName(number);
        this.suit = suit;
    }

    private String numberToName(int number) {
        if(number == ONE) return "A";
        if(number == ELEVEN) return "J";
        if(number == TWELVE) return "Q";
        if(number == THIRTEEN) return "K";

        return String.valueOf(number);
    }

    private int numberToValidNumber(int number) {
        return (number > TEN) ? TEN : number;
    }

    //TODO
    // ACE는 현재 무조건 1, 핸드에서 합산 시에 버스트가 아닌 경우에 10을 더해서 ACE가 곧 11로 계산되는 셈
    boolean isAce() {
        return this.number == ONE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return suit == card.suit &&
                Objects.equals(name, card.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, name);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("suit", suit)
                .toString();
    }

}
