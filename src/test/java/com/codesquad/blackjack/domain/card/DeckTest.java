package com.codesquad.blackjack.domain.card;

import com.codesquad.blackjack.domain.Suit;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class DeckTest {

    private static final Logger log = LoggerFactory.getLogger(DeckTest.class);

    @Test
    public void 오토() {
        Deck autoDeck = Deck.auto();

        assertThat(autoDeck.getDeck().size()).isEqualTo(52);

        for (int i = 0; i < 52; i++) {
            log.debug("{}", autoDeck.draw());
        }
    }

    @Test
    public void 매뉴얼() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.TWO, Suit.DIAMOND));
        cards.add(new Card(Rank.SEVEN, Suit.CLUB));

        Deck manualDeck = Deck.manual(cards);

        assertThat(manualDeck.getDeck().size()).isEqualTo(2);

        for (int i = 0; i < 2; i++) {
            log.debug("{}", manualDeck.draw());
        }
    }

}