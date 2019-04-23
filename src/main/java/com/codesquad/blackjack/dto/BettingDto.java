package com.codesquad.blackjack.dto;

import com.codesquad.blackjack.domain.MessageType;

import static com.codesquad.blackjack.domain.MessageType.BETTING;

public class BettingDto {

    private MessageType type = BETTING;
    private String status;

    public BettingDto() {
    }

    public BettingDto(String status) {
        this.status = status;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
