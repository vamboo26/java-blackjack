package com.codesquad.blackjack.domain.card;

import java.util.Objects;

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

    int getNumber() {
        return number;
    }

    String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return number == card.number &&
                Objects.equals(suit, card.suit) &&
                Objects.equals(name, card.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, suit, name);
    }

    @Override
    public String toString() {
        return "Card{" +
                "number=" + number +
                ", suit='" + suit + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
