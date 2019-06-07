package com.codesquad.blackjack.domain.player;

import com.codesquad.blackjack.domain.Suit;
import com.codesquad.blackjack.domain.card.Card;
import com.codesquad.blackjack.domain.card.Deck;
import com.codesquad.blackjack.domain.card.Rank;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class DealerTest {

    @Test
    public void 딜러턴_17미만() {
        Dealer dealer = new Dealer();
        Deck deck = Deck.auto();

        dealer.receiveCard(new Card(Rank.TEN, Suit.DIAMOND));
        dealer.receiveCard(new Card(Rank.TWO, Suit.DIAMOND));

        dealer.processTurn(deck);

        assertThat(dealer.getCards().getTotal()).isNotEqualTo(12);
    }

    @Test
    public void 딜러턴_17이상() {
        Dealer dealer = new Dealer();
        Deck deck = Deck.auto();

        dealer.receiveCard(new Card(Rank.TEN, Suit.DIAMOND));
        dealer.receiveCard(new Card(Rank.SEVEN, Suit.DIAMOND));

        dealer.processTurn(deck);

        assertThat(dealer.getCards().getTotal()).isEqualTo(17);
    }

}