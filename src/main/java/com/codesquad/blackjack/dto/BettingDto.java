package com.codesquad.blackjack.dto;

import com.codesquad.blackjack.domain.ResponseType;

import static com.codesquad.blackjack.domain.ResponseType.BETTING;

public class BettingDto {

    private ResponseType type = BETTING;
    private String status;

    public BettingDto() {
    }

    public BettingDto(String status) {
        this.status = status;
    }

    public ResponseType getType() {
        return type;
    }

    public void setType(ResponseType type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
