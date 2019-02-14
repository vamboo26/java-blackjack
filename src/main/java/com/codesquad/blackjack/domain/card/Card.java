package com.codesquad.blackjack.domain.card;

import java.util.Objects;

public class Card {
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
        if(number == 1) return "A";
        if(number == 11) return "J";
        if(number == 12) return "Q";
        if(number == 13) return "K";

        return String.valueOf(number);
    }

    private int numberToValidNumber(int number) {
        return (number > 10) ? 10 : number;
    }

    int getNumber() {
        return number;
    }

    public boolean isAce() {
        return this.number == 1;
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
