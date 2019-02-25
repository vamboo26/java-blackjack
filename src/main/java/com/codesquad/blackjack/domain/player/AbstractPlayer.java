package com.codesquad.blackjack.domain.player;

import com.codesquad.blackjack.domain.card.Card;
import com.codesquad.blackjack.domain.card.Cards;
import com.codesquad.blackjack.dto.CardsDto;

public abstract class AbstractPlayer implements Player {
    private String name;
    private Cards cards = new Cards();

    public AbstractPlayer(String name) {
        this.name = name;
    }

    protected String getName() {
        return name;
    }

    protected Cards getCards() {
        return cards;
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

    @Override
    public CardsDto getCardsDto() {
        return this.cards._toCardsDto();
    }
}
