package com.codesquad.blackjack.domain.card;

import com.codesquad.blackjack.domain.Suit;
import com.codesquad.blackjack.support.BaseTest;
import org.junit.Test;

public class CardTest extends BaseTest {

    @Test
    public void 카드생성_정상() {
        assertCard(new Card(1, Suit.DIAMOND), "A", Suit.DIAMOND);
        assertCard(new Card(11, Suit.HEART), "J", Suit.HEART);
        assertCard(new Card(12, Suit.SPADE), "Q", Suit.SPADE);
        assertCard(new Card(13, Suit.CLUB), "K", Suit.CLUB);
    }

    @Test(expected = IllegalArgumentException.class)
    public void 카드생성_비정상() {
        assertCard(new Card(0, Suit.DIAMOND), "A", Suit.DIAMOND);
        assertCard(new Card(14, Suit.DIAMOND), "A", Suit.DIAMOND);
    }

    private void assertCard(Card card, String name, Suit suit) {
        softly.assertThat(card.getName()).isEqualTo(name);
        softly.assertThat(card.getSuit()).isEqualTo(suit);
    }

}