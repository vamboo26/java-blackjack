package com.codesquad.blackjack.domain.card;

import com.google.common.base.MoreObjects;
import lombok.Getter;

@Getter
public class Card {

    public static final int ONE = 1;
    public static final int TEN = 10;
    public static final int ELEVEN = 11;
    public static final int TWELVE = 12;
    public static final int THIRTEEN = 13;

    private int number;
    private String suit;
    private String name;

    private Card(int number, String suit) {
        this.number = numberToValidNumber(number);
        this.suit = suit;
        this.name = numberToName(number);
    }

    public static Card of(int number, String suit) {
        return new Card(number, suit);
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

    boolean isAce() {
        return this.number == ONE;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("number", number)
                .add("suit", suit)
                .add("name", name)
                .toString();
    }

}
