package com.codesquad.blackjack.domain.user;

import com.codesquad.blackjack.domain.Chip;
import com.codesquad.blackjack.domain.card.Card;
import com.codesquad.blackjack.domain.card.Cards;
import com.codesquad.blackjack.dto.CardsDto;
import com.codesquad.blackjack.dto.UserDto;

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

    public void receiveCard(Card card) {
        this.cards.add(card);
    }

    public void dealerTurn(Card card) {
        while(this.cards.isDealerHit()) {
            receiveCard(card);
        }
    }

    public void betChip(int bettingChip) {
        this.chip = chip.subtract(bettingChip);
    }

    public void winPrize(Chip prize) {
        this.chip = chip.sum(prize);
    }

    public boolean checkChip(int bettingChip) {
        return this.chip.isOver(bettingChip);
    }

    public boolean isBankruptcy() {
        return this.chip.isZero();
    }

    public boolean isWinner(User target) {
        return this.cards.isBigger(target.cards);
    }

    public boolean isTie(User target) {
        return this.cards.isTie(target.cards);
    }

    public boolean isBurst() {
        return this.cards.isBurst();
    }

    public boolean isBlackjack() {
        return this.cards.isBlackjack();
    }

    public CardsDto getCardsDto() {
        return this.cards._toCardsDto();
    }

    public UserDto _toUserDto() {
        return new UserDto(name, cards._toCardsDto(), chip);
    }
}
