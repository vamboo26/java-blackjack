package com.codesquad.blackjack.domain.card;

import com.codesquad.blackjack.domain.Suit;
import lombok.Getter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static com.codesquad.blackjack.domain.Chip.ZERO;

@Getter
public class Deck {

    private List<Card> deck;

    private Deck(List<Card> deck) {
        this.deck = deck;
    }

    public static Deck auto() {
        List<Card> deck = generateDeck();
        Collections.shuffle(deck);
        return new Deck(deck);
    }

    public static Deck manual(List<Card> deck) {
        return new Deck(deck);
    }

    public Card draw() {
        return deck.remove(ZERO);
    }

    private static List<Card> generateDeck() {
        List<Card> deck = new LinkedList<>();

        for (Suit suit : Suit.values()) {
            for (Rank value : Rank.values()) {
                deck.add(new Card(value, suit));
            }
        }

        return deck;
    }

}
