package com.codesquad.blackjack.domain.card;

import com.codesquad.blackjack.domain.Suit;
import com.codesquad.blackjack.support.BaseTest;
import org.junit.Test;

public class CardTest extends BaseTest {

    @Test
    public void 카드생성_정상() {
        assertCard(new Card(Rank.ACE, Suit.DIAMOND), Rank.ACE, Suit.DIAMOND);
        assertCard(new Card(Rank.JACK, Suit.HEART), Rank.JACK, Suit.HEART);
        assertCard(new Card(Rank.QUEEN, Suit.SPADE), Rank.QUEEN, Suit.SPADE);
        assertCard(new Card(Rank.KING, Suit.CLUB), Rank.KING, Suit.CLUB);
    }

    private void assertCard(Card card, Rank rank, Suit suit) {
        softly.assertThat(card.getRank()).isEqualTo(rank);
        softly.assertThat(card.getSuit()).isEqualTo(suit);
    }

}