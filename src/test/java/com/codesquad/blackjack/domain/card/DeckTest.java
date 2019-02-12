package com.codesquad.blackjack.domain.card;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static com.codesquad.blackjack.domain.Suit.CLUB;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class DeckTest {

    @Test
    public void drawTest() {
        List<Card> temp = new LinkedList<>();
        temp.add(Card.of(1, CLUB.toString()));
        Deck deck = Deck.manual(temp);

        assertThat(deck.draw()).isEqualTo(Card.of(1, CLUB.toString()));
    }
}