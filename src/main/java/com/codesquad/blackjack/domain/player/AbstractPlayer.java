package com.codesquad.blackjack.domain.player;

import com.codesquad.blackjack.domain.card.Card;
import com.codesquad.blackjack.domain.card.Cards;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@MappedSuperclass
public abstract class AbstractPlayer implements Player {

    @Size(min = 2, max = 10)
    @Column
    private String name;

    @Transient
    private Cards cards = new Cards();

    AbstractPlayer() {
    }

    AbstractPlayer(String name) {
        this.name = name;
    }

    @Override
    public void initialize() {
        this.cards = new Cards() ;
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
