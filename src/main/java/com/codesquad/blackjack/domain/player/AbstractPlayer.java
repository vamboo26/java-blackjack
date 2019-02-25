package com.codesquad.blackjack.domain.player;

import com.codesquad.blackjack.domain.card.Card;
import com.codesquad.blackjack.domain.card.Cards;
import com.codesquad.blackjack.dto.CardsDto;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Size;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractPlayer implements Player {

    @Size(min = 2, max = 10)
    @Column
    private String name;

    @Transient
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
