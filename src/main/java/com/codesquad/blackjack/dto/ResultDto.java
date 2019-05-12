package com.codesquad.blackjack.dto;

import com.codesquad.blackjack.domain.ResponseType;

import static com.codesquad.blackjack.domain.ResponseType.RESULT;

public class ResultDto {

    private ResponseType type = RESULT;
    private String status;
    private String winner;

    public ResultDto() {
    }

    public ResultDto(String status, String winner) {
        this.status = status;
        this.winner = winner;
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

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

}
