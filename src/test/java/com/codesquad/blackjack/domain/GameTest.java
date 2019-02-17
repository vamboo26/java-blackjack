package com.codesquad.blackjack.domain;

import com.codesquad.blackjack.domain.card.Card;
import com.codesquad.blackjack.domain.card.Deck;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static com.codesquad.blackjack.domain.Suit.CLUB;
import static com.codesquad.blackjack.domain.Suit.DIAMOND;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class GameTest {
    private Game game = new Game("player");

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

        Deck deck = Deck.manual(cards);

        Game newGame = new Game("player");
        newGame.init(deck, 100);

        assertThat(newGame.end(Chip.of(200))).isEqualTo("player");
    }

    @Test
    public void endDealerWinTest() {
        List<Card> cards = new LinkedList<>();

        cards.add(Card.of(4, CLUB.toString()));
        cards.add(Card.of(3, CLUB.toString()));
        cards.add(Card.of(10, CLUB.toString()));
        cards.add(Card.of(10, DIAMOND.toString()));

        Deck deck = Deck.manual(cards);

        Game newGame = new Game("player");
        newGame.init(deck, 100);

        assertThat(newGame.end(Chip.of(200))).isEqualTo("dealer");
    }

    @Test
    public void endTieTest() {
        List<Card> cards = new LinkedList<>();
        cards.add(Card.of(4, CLUB.toString()));
        cards.add(Card.of(4, DIAMOND.toString()));
        cards.add(Card.of(10, CLUB.toString()));
        cards.add(Card.of(10, DIAMOND.toString()));

        Deck deck = Deck.manual(cards);

        Game newGame = new Game("player");
        newGame.init(deck, 100);

        assertThat(newGame.end(Chip.of(200))).isEqualTo("무승부");
    }

}

//    public void dealerTurn(Deck deck) {
//        while(dealer.getTotal() < 17) {
//            dealer.receiveCard(deck.draw());
//        }
//    }
