package com.codesquad.blackjack.dto;

import com.codesquad.blackjack.domain.ResponseType;

public class DealerDto {

    private ResponseType type;
    private String name;
    private CardsDto cards;

    public DealerDto() {
    }

    public DealerDto(ResponseType type, String name, CardsDto cards) {
        this.type = type;
        this.name = name;
        this.cards = cards;
    }

    public ResponseType getType() {
        return type;
    }

    public void setType(ResponseType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CardsDto getCards() {
        return cards;
    }

    public void setCards(CardsDto cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "DealerDto{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", cards=" + cards +
                '}';
    }

}

