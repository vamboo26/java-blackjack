package com.codesquad.blackjack.domain;

import com.codesquad.blackjack.domain.card.Card;
import com.codesquad.blackjack.domain.card.Deck;
import com.codesquad.blackjack.domain.user.User;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static com.codesquad.blackjack.domain.Suit.CLUB;
import static com.codesquad.blackjack.domain.Suit.DIAMOND;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class GameTest {
    private Game game = new Game();

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void initTest() {
        game.init(Deck.auto(), 100);
        assertThat(Chip.of(200)).isEqualTo(game.getNormalPrize());
    }

    @Test
    public void endPlayerWinTest() {
        List<Card> cards = new LinkedList<>();
        cards.add(Card.of(3, CLUB.toString()));
        cards.add(Card.of(4, CLUB.toString()));
        cards.add(Card.of(10, CLUB.toString()));
        cards.add(Card.of(10, DIAMOND.toString()));

        //player getter로? 승자판별자체를 이름을 반환하도록하면 테스트 쉬워진다
        User player = new User();
        player.receiveCard(Card.of(4, CLUB.toString()));
        player.receiveCard(Card.of(10, DIAMOND.toString()));

        Deck deck = Deck.manual(cards);

        Game newGame = new Game();
        newGame.init(deck, 100);

        assertThat(newGame.end(Chip.of(200))).isEqualTo(player);
    }

    @Test
    public void endDealerWinTest() {
        List<Card> cards = new LinkedList<>();

        cards.add(Card.of(4, CLUB.toString()));
        cards.add(Card.of(3, CLUB.toString()));
        cards.add(Card.of(10, CLUB.toString()));
        cards.add(Card.of(10, DIAMOND.toString()));

        User dealer = new User();
        dealer.receiveCard(Card.of(4, CLUB.toString()));
        dealer.receiveCard(Card.of(10, CLUB.toString()));

        Deck deck = Deck.manual(cards);

        Game newGame = new Game();
        newGame.init(deck, 100);

        assertThat(newGame.end(Chip.of(200))).isEqualTo(dealer);
    }

    @Test
    public void endTieTest() {
        List<Card> cards = new LinkedList<>();
        cards.add(Card.of(4, CLUB.toString()));
        cards.add(Card.of(4, DIAMOND.toString()));
        cards.add(Card.of(10, CLUB.toString()));
        cards.add(Card.of(10, DIAMOND.toString()));

        Deck deck = Deck.manual(cards);

        Game newGame = new Game();
        newGame.init(deck, 100);

        assertThat(newGame.end(Chip.of(200))).isEqualTo(Optional.empty());
    }

}

//    public void dealerTurn(Deck deck) {
//        while(dealer.getTotal() < 17) {
//            dealer.receiveCard(deck.draw());
//        }
//    }
