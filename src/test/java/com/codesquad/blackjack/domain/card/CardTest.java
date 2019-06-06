package com.codesquad.blackjack.domain.card;

import com.codesquad.blackjack.domain.Suit;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class CardTest {

    @Test
    public void 카드생성_테스트() {
        assertCard(new Card(1, Suit.DIAMOND), "A", Suit.DIAMOND);
        assertCard(new Card(11, Suit.HEART), "J", Suit.HEART);
        assertCard(new Card(12, Suit.SPADE), "Q", Suit.SPADE);
        assertCard(new Card(13, Suit.CLUB), "K", Suit.CLUB);
    }

    private void assertCard(Card card, String name, Suit suit) {
        assertThat(card.getName()).isEqualTo(name);
        assertThat(card.getSuit()).isEqualTo(suit);
    }

}