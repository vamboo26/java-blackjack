package com.codesquad.blackjack.domain.user;

import com.codesquad.blackjack.domain.Chip;
import com.codesquad.blackjack.domain.card.Card;
import com.codesquad.blackjack.domain.card.Cards;
import com.codesquad.blackjack.dto.CardsDto;

public class User {
    private static final int DEFAULT_CHIP_AMOUNT = 500;

    private Cards cards = new Cards();
    private Chip chip = Chip.of(DEFAULT_CHIP_AMOUNT);

    public Card receiveCard(Card card) {
        cards.add(card);
        return card;
    }

    public CardsDto getCardsDto() {
        return cards._toCardsDto();
    }

    public int getTotal() {
        return cards.calculateTotal();
    }

    public void winPrize(Chip prize) {
        chip.sum(prize);
    }

    public void initializeCards() {
        this.cards = new Cards();
    }
}
