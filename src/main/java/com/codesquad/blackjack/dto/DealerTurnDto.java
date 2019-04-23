package com.codesquad.blackjack.dto;

import com.codesquad.blackjack.domain.MessageType;

import static com.codesquad.blackjack.domain.MessageType.DEALERTURN;

public class DealerTurnDto {

    private MessageType type = DEALERTURN;
    private String status;

    public DealerTurnDto(String status) {
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
        return "DealerTurnDto{" +
                "type=" + type +
                ", status='" + status + '\'' +
                '}';
    }

}
