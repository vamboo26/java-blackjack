package com.codesquad.blackjack.socket;

import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Getter
public class SocketResponse<T> {

    private String type;
    private T response;

    public SocketResponse(String type, T response) {
        this.type = type;
        this.response = response;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("type", type)
                .append("response", response)
                .toString();
    }

}
