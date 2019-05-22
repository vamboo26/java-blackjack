package com.codesquad.blackjack.domain.player;

import com.codesquad.blackjack.domain.card.Card;
import com.codesquad.blackjack.domain.card.Cards;
import lombok.Getter;

@Getter
public class Dealer implements Player {

    private static final String DEALER_NAME = "DEALER";

    private Cards cards = new Cards();

    public boolean dealerTurn() {
        return getCards().isDealerHit();
    }

    public boolean isWinner(User target) {
        return getCards().isBigger(target.getCards());
    }

    public boolean isTie(User target) {
        return getCards().isTie(target.getCards());
    }

    @Override
    public void initialize() {
        this.cards = new Cards();
    }

    @Override
    public void receiveCard(Card card) {
        this.cards.add(card);
    }

    @Override
    public boolean isBurst() {
        return this.cards.isBurst();
    }

    @Override
    public boolean isBlackjack() {
        return this.cards.isBlackjack();
    }

}