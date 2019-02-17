package com.codesquad.blackjack.domain.card;

import org.junit.Test;

import static com.codesquad.blackjack.domain.Suit.CLUB;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class CardsTest {
    public static final Cards HAS_ACE_OVER_21 = new Cards();
    public static final Cards HAS_ACE_UNDER_21 = new Cards();
    public static final Cards NO_ACE = new Cards();

    static {
        HAS_ACE_OVER_21.add(Card.of(1, CLUB.toString()));
        HAS_ACE_OVER_21.add(Card.of(2, CLUB.toString()));
        HAS_ACE_OVER_21.add(Card.of(10, CLUB.toString()));

        HAS_ACE_UNDER_21.add(Card.of(1, CLUB.toString()));
        HAS_ACE_UNDER_21.add(Card.of(2, CLUB.toString()));

        NO_ACE.add(Card.of(5, CLUB.toString()));
        NO_ACE.add(Card.of(10, CLUB.toString()));
    }

    @Test
    public void hasAce() {
        assertThat(HAS_ACE_OVER_21.hasAce()).isTrue();
        assertThat(HAS_ACE_UNDER_21.hasAce()).isTrue();
        assertThat(NO_ACE.hasAce()).isFalse();
    }

    @Test
    public void calculateTotalTest() {
        assertThat(HAS_ACE_OVER_21.calculateTotal()).isEqualTo(13);
        assertThat(HAS_ACE_UNDER_21.calculateTotal()).isEqualTo(13);
        assertThat(NO_ACE.calculateTotal()).isEqualTo(15);
    }
}