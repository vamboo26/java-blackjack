package com.codesquad.blackjack.domain.player;

import com.codesquad.blackjack.domain.Chip;
import com.codesquad.blackjack.dto.UserDto;

public class Gamer extends AbstractPlayer {
    private static final int DEFAULT_CHIP_AMOUNT = 500;

    private Chip chip = new Chip(DEFAULT_CHIP_AMOUNT);

    public Gamer(String name) {
        super(name);
    }

    public Gamer(String name, Chip chip) {
        super(name);
        this.chip = chip;
    }

    @Override
    public Gamer initialize() {
        return new Gamer(getName(), chip);
    }

    @Override
    public UserDto _toUserDto() {
        return new UserDto(getName(), getCardsDto(), this.chip);
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
}