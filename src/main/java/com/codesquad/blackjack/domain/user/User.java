package com.codesquad.blackjack.domain.user;

import com.codesquad.blackjack.domain.Chip;
import com.codesquad.blackjack.domain.card.Card;
import com.codesquad.blackjack.domain.card.Cards;
import com.codesquad.blackjack.dto.CardsDto;
import com.codesquad.blackjack.dto.UserDto;

import java.util.Objects;

public class User {
    private static final int DEFAULT_CHIP_AMOUNT = 500;

    private String name;
    private Cards cards = new Cards();
    private Chip chip = new Chip(DEFAULT_CHIP_AMOUNT);

    public User(String name) {
        this.name = name;
    }

    public User(String name, Chip chip) {
        this.name = name;
        this.chip = chip;
    }

    public User initialize() {
        return new User(name, chip);
    }

    public Card receiveCard(Card card) {
        cards.add(card);
        return card;
    }

    public void winPrize(Chip prize) {
        chip.sum(prize);
    }

    public int getTotal() {
        return cards.calculateTotal();
    }

    public CardsDto getCardsDto() {
        return cards._toCardsDto();
    }

    public UserDto _toUserDto() {
        return new UserDto(name, chip);
    }

    public boolean checkChip(int bettingChip) {
        return chip.isOver(bettingChip);
    }

    public void betChip(int bettingChip) {
        this.chip = chip.substract(bettingChip);
    }

    public boolean isBankruptcy() {
        return chip.isZero();
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

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
