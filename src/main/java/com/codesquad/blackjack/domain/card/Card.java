package com.codesquad.blackjack.domain.card;

import java.util.Objects;

public class Card {
    private final int number;
    private final String suit;

    private Card(int number, String suit) {
        this.number = number;
        this.suit = suit;
    }

    public static Card of(int number, String suit) {
        return new Card(number, suit);
    }
//
//    public int sum(Card target) {
//        return this.number + target.number;
//    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return number == card.number &&
                Objects.equals(suit, card.suit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, suit);
    }

    @Override
    public String toString() {
        return "Card{" +
                "number=" + number +
                ", suit='" + suit + '\'' +
                '}';
    }
}
