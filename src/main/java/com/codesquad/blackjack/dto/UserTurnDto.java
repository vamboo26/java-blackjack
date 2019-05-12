package com.codesquad.blackjack.dto;

import com.codesquad.blackjack.domain.ResponseType;

import static com.codesquad.blackjack.domain.ResponseType.USERTURN;

public class UserTurnDto {

    private ResponseType type = USERTURN;
    private String status;

    public UserTurnDto(String status) {
        this.status = status;
    }

    public ResponseType getType() {
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
