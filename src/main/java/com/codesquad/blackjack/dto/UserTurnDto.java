package com.codesquad.blackjack.dto;

import com.codesquad.blackjack.domain.MessageType;

import static com.codesquad.blackjack.domain.MessageType.USERTURN;

public class UserTurnDto {

    private MessageType type = USERTURN;
    private String status;

    public UserTurnDto(String status) {
        this.status = status;
    }

    public MessageType getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "UserTurnDto{" +
                "type=" + type +
                ", status='" + status + '\'' +
                '}';
    }

}
