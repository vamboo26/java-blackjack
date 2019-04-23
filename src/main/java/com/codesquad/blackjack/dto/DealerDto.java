package com.codesquad.blackjack.dto;

import com.codesquad.blackjack.domain.MessageType;

public class DealerDto {

    private MessageType type;
    private String name;
    private CardsDto cards;

    public DealerDto() {
    }

    public DealerDto(MessageType type, String name, CardsDto cards) {
        this.type = type;
        this.name = name;
        this.cards = cards;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
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

