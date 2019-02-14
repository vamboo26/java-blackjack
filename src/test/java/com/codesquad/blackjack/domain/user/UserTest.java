package com.codesquad.blackjack.domain.user;

import com.codesquad.blackjack.domain.card.Card;
import org.junit.Before;
import org.junit.Test;

import static com.codesquad.blackjack.domain.Suit.CLUB;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class UserTest {
    private User user = new User();

    @Before
    public void setUp() throws Exception {
        Card card = Card.of(1, CLUB.toString());
        user.receiveCard(card);

    }

    @Test
    public void receiveCardTest() {
        User user = new User();
        Card card = Card.of(1, CLUB.toString());

        user.receiveCard(card);
        assertThat(user.getCardsDto().getCards().get(0)).isEqualTo(card);

        user.initializeCards();
        assertThat(user).isEqualTo(new User());
    }

    @Test
    public void initializeCardsTest() {
        User user = new User();
        Card card = Card.of(1, CLUB.toString());
        user.receiveCard(card);

        user.initializeCards();
        assertThat(user).isEqualTo(new User());
    }
}