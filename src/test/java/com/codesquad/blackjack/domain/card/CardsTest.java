package com.codesquad.blackjack.domain.card;

import com.codesquad.blackjack.domain.Suit;
import com.codesquad.blackjack.support.BaseTest;
import org.junit.Test;

public class CardsTest extends BaseTest {

    @Test
    public void 카드합산_테스트() {
        Cards cards = new Cards();
        cards.add(new Card(Rank.TEN, Suit.DIAMOND));
        cards.add(new Card(Rank.TEN, Suit.DIAMOND));

        softly.assertThat(cards.getTotal()).isEqualTo(20);
    }

    @Test
    public void 카드합산_에이스가_1로() {
        Cards cards = new Cards();
        cards.add(new Card(Rank.ACE, Suit.DIAMOND));
        cards.add(new Card(Rank.TEN, Suit.DIAMOND));

        softly.assertThat(cards.getTotal()).isEqualTo(21);
    }

    @Test
    public void 카드합산_에이스가_11로() {
        Cards cards = new Cards();
        cards.add(new Card(Rank.ACE, Suit.DIAMOND));
        cards.add(new Card(Rank.NINE, Suit.DIAMOND));
        cards.add(new Card(Rank.NINE, Suit.DIAMOND));

        softly.assertThat(cards.getTotal()).isEqualTo(19);
    }

    @Test
    public void 핸드_블랙잭() {
        Cards cards = new Cards();
        cards.add(new Card(Rank.ACE, Suit.DIAMOND));
        cards.add(new Card(Rank.TEN, Suit.DIAMOND));

        softly.assertThat(cards.getHand()).isEqualTo(Cards.Hand.BLACKJACK);
    }

    @Test
    public void 핸드_버스트() {
        Cards cards = new Cards();
        cards.add(new Card(Rank.FIVE, Suit.DIAMOND));
        cards.add(new Card(Rank.SEVEN, Suit.DIAMOND));
        cards.add(new Card(Rank.QUEEN, Suit.DIAMOND));

        softly.assertThat(cards.getHand()).isEqualTo(Cards.Hand.BUST);
    }

    @Test
    public void 핸드_노말() {
        Cards cards = new Cards();
        cards.add(new Card(Rank.FOUR, Suit.DIAMOND));
        cards.add(new Card(Rank.SIX, Suit.DIAMOND));

        softly.assertThat(cards.getHand()).isEqualTo(Cards.Hand.NORMAL);
    }

}