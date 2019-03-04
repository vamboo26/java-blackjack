package com.codesquad.blackjack.dto;

import com.codesquad.blackjack.MessageType;

public class ResultDto {
    private MessageType type;
    private String winner;

    public ResultDto() {
    }

    public ResultDto(MessageType type, String winner) {
        this.type = type;
        this.winner = winner;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
