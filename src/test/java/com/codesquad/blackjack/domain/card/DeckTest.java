package com.codesquad.blackjack.domain.card;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

import static com.codesquad.blackjack.domain.card.CardTest.ACE_CLUB_CARD;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class DeckTest {
    private static final Logger log = LoggerFactory.getLogger(DeckTest.class);

    public static final Deck DECK1;

    static {
        List<Card> oneSize = new LinkedList<>();
        oneSize.add(ACE_CLUB_CARD);

        DECK1 = Deck.manual(oneSize);
    }

    @Test
    public void drawTest() {
        assertThat(DECK1.draw()).isEqualTo(ACE_CLUB_CARD);
    }

    @Test
    public void autoTest() throws Exception {
        Deck deck = Deck.auto();

        try {
            for (int i = 1; i < 999 ; i++) {
                log.debug("{} 번째 카드 : {}", i, deck.draw());
            }
        } catch(Exception e) {
            log.debug("끝났소");
        }
    }
}