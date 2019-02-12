package com.codesquad.blackjack.domain.card;

import com.codesquad.blackjack.domain.Suit;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Deck {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 13;

    private List<Card> deck;

    private Deck(List<Card> deck) {
        this.deck = deck;
        Collections.shuffle(deck);
    }

    public static Deck auto() {
        List<Card> deck = new LinkedList<>();
        generateDeck(deck);
        return new Deck(deck);
    }

    public static Deck manual(List<Card> deck) {
        return new Deck(deck);
    }

    public Card draw() {
        return deck.remove(0);
    }

    private static void generateDeck(List<Card> deck) {
        for (Suit suit : Suit.values()) {
            generateCards(deck, suit);
        }
    }

    private static void generateCards(List<Card> deck, Suit suit) {
        for (int i = MIN_NUMBER; i <= MAX_NUMBER; i++) {
            deck.add(Card.of(i, suit.toString()));
        }
    }
}
