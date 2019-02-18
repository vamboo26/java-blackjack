package com.codesquad.blackjack.domain.user;

import com.codesquad.blackjack.domain.card.Card;
import org.junit.Before;
import org.junit.Test;

import static com.codesquad.blackjack.domain.Suit.CLUB;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class UserTest {
    private User user;
    private Card card;

    @Before
    public void setUp() throws Exception {
        user = new User("finn");
        card = Card.of(1, CLUB.toString());
    }

    @Test
    public void receiveCardTest() {
        user.receiveCard(card);

        assertThat(user.getCardsDto().getCards().get(0)).isEqualTo(card);
    }

    @Test
    public void initializeCardsTest() {
        user.receiveCard(card);
        user.initializeCards();

        assertThat(user).isEqualTo(new User("finn"));
    }
}