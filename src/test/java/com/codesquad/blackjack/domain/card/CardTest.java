package com.codesquad.blackjack.domain.card;

import org.junit.Test;

import static com.codesquad.blackjack.domain.Suit.CLUB;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class CardTest {
    public static final Card ACE_CLUB_CARD = Card.of(1, CLUB.toString());
    public static final Card JACK_CLUB_CARD = Card.of(11, CLUB.toString());
    public static final Card QUEEN_CLUB_CARD = Card.of(12, CLUB.toString());
    public static final Card KING_CLUB_CARD = Card.of(13, CLUB.toString());

    @Test
    public void ofATest() {
        assertThat(ACE_CLUB_CARD.getName()).isEqualTo("A");
        assertThat(ACE_CLUB_CARD.getNumber()).isEqualTo(1);
    }

    @Test
    public void ofJTest() {
        assertThat(JACK_CLUB_CARD.getName()).isEqualTo("J");
        assertThat(JACK_CLUB_CARD.getNumber()).isEqualTo(10);
    }

    @Test
    public void ofQTest() {
        assertThat(QUEEN_CLUB_CARD.getName()).isEqualTo("Q");
        assertThat(QUEEN_CLUB_CARD.getNumber()).isEqualTo(10);
    }

    @Test
    public void ofKTest() {
        assertThat(KING_CLUB_CARD.getName()).isEqualTo("K");
        assertThat(KING_CLUB_CARD.getNumber()).isEqualTo(10);
    }

    @Test
    public void isAceTest() {
        assertThat(ACE_CLUB_CARD.isAce()).isTrue();
    }
}