package com.codesquad.blackjack.domain.card;

import org.junit.Before;
import org.junit.Test;

import static com.codesquad.blackjack.domain.Suit.CLUB;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class CardsTest {
    private Cards hasAceOver21 = new Cards();
    private Cards hasAceUnder21 = new Cards();
    private Cards noAce = new Cards();

    @Before
    public void setUp() throws Exception {
        hasAceOver21.add(Card.of(1, CLUB.toString()));
        hasAceOver21.add(Card.of(2, CLUB.toString()));
        hasAceOver21.add(Card.of(10, CLUB.toString()));

        hasAceUnder21.add(Card.of(1, CLUB.toString()));
        hasAceUnder21.add(Card.of(2, CLUB.toString()));

        noAce.add(Card.of(5, CLUB.toString()));
        noAce.add(Card.of(10, CLUB.toString()));
    }

    @Test
    public void hasAce() {
        assertThat(hasAceOver21.hasAce()).isTrue();
        assertThat(hasAceUnder21.hasAce()).isTrue();
        assertThat(noAce.hasAce()).isFalse();
    }

    @Test
    public void calculateTotalTest() {
        assertThat(hasAceOver21.calculateTotal()).isEqualTo(13);
        assertThat(hasAceUnder21.calculateTotal()).isEqualTo(13);
        assertThat(noAce.calculateTotal()).isEqualTo(15);
    }
}