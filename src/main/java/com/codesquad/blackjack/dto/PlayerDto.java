package com.codesquad.blackjack.dto;

import com.codesquad.blackjack.domain.Chip;

public class PlayerDto {
    private String name;
    private CardsDto cards;
    private Chip chip;

    public PlayerDto(String name, CardsDto cards) {
        this.name = name;
        this.cards = cards;
    }

    public PlayerDto(String name, CardsDto cards, Chip chip) {
        this.name = name;
        this.cards = cards;
        this.chip = chip;
    }

    public String getName() {
        return name;
    }

    public CardsDto getCards() {
        return cards;
    }

    public Chip getChip() {
        return chip;
    }

    @Override
    public String toString() {
        return "PlayerDto{" +
                "name='" + name + '\'' +
                ", chip=" + chip +
                '}';
    }
}
