package com.codesquad.blackjack.domain.card;

import com.codesquad.blackjack.domain.Suit;
import com.codesquad.blackjack.support.BaseTest;
import org.junit.Test;

public class CardsTest extends BaseTest {

    @Test
    public void 카드합산_테스트() {
        Cards cards = new Cards();
        cards.add(new Card(10, Suit.DIAMOND));
        cards.add(new Card(10, Suit.DIAMOND));

        softly.assertThat(cards.getTotal()).isEqualTo(20);
    }

    @Test
    public void 카드합산_에이스가_1로() {
        Cards cards = new Cards();
        cards.add(new Card(1, Suit.DIAMOND));
        cards.add(new Card(13, Suit.DIAMOND));

        softly.assertThat(cards.getTotal()).isEqualTo(21);
    }

    @Test
    public void 카드합산_에이스가_11로() {
        Cards cards = new Cards();
        cards.add(new Card(1, Suit.DIAMOND));
        cards.add(new Card(9, Suit.DIAMOND));
        cards.add(new Card(8, Suit.DIAMOND));

        softly.assertThat(cards.getTotal()).isEqualTo(18);
    }

    @Test
    public void 핸드_블랙잭() {
        Cards cards = new Cards();
        cards.add(new Card(1, Suit.DIAMOND));
        cards.add(new Card(13, Suit.DIAMOND));

        softly.assertThat(cards.hand()).isEqualTo(Cards.Hand.BLACKJACK);
    }

    @Test
    public void 핸드_버스트() {
        Cards cards = new Cards();
        cards.add(new Card(5, Suit.DIAMOND));
        cards.add(new Card(7, Suit.DIAMOND));
        cards.add(new Card(13, Suit.DIAMOND));

        softly.assertThat(cards.hand()).isEqualTo(Cards.Hand.BURST);
    }

    @Test
    public void 핸드_노말() {
        Cards cards = new Cards();
        cards.add(new Card(4, Suit.DIAMOND));
        cards.add(new Card(6, Suit.DIAMOND));

        softly.assertThat(cards.hand()).isEqualTo(Cards.Hand.NORMAL);
    }

}