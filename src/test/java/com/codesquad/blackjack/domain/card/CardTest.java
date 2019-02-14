package com.codesquad.blackjack.domain.card;

import org.junit.Test;

import static com.codesquad.blackjack.domain.Suit.CLUB;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class CardTest {

    @Test
    public void ofATest() {
        Card card = Card.of(1, CLUB.toString());
        assertThat(card.getName()).isEqualTo("A");
        assertThat(card.getNumber()).isEqualTo(1);
    }

    @Test
    public void ofJTest() {
        Card card = Card.of(11, CLUB.toString());
        assertThat(card.getName()).isEqualTo("J");
        assertThat(card.getNumber()).isEqualTo(10);
    }

    @Test
    public void ofQTest() {
        Card card = Card.of(12, CLUB.toString());
        assertThat(card.getName()).isEqualTo("Q");
        assertThat(card.getNumber()).isEqualTo(10);
    }

    @Test
    public void ofKTest() {
        Card card = Card.of(13, CLUB.toString());
        assertThat(card.getName()).isEqualTo("K");
        assertThat(card.getNumber()).isEqualTo(10);
    }

    @Test
    public void isAceTest() {
        Card card = Card.of(1, CLUB.toString());
        assertThat(card.isAce()).isTrue();
    }
}