package com.codesquad.blackjack.socket;

import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Getter
public class SocketRequest<T> {

    private String type;
    private T request;

    public SocketRequest(String type, T request) {
        this.type = type;
        this.request = request;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("type", type)
                .append("request", request)
                .toString();
    }

}
