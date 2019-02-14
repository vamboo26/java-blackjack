package com.codesquad.blackjack.domain.user;

import com.codesquad.blackjack.domain.Chip;
import com.codesquad.blackjack.domain.card.Card;
import com.codesquad.blackjack.domain.card.Cards;
import com.codesquad.blackjack.dto.CardsDto;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(cards, user.cards) &&
                Objects.equals(chip, user.chip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards, chip);
    }
}
