package com.codesquad.blackjack.dto;

import com.codesquad.blackjack.domain.ResponseType;

import static com.codesquad.blackjack.domain.ResponseType.DEALERTURN;

public class DealerTurnDto {

    private ResponseType type = DEALERTURN;
    private String status;

    public DealerTurnDto(String status) {
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
        return "DealerTurnDto{" +
                "type=" + type +
                ", status='" + status + '\'' +
                '}';
    }

}
