package com.codesquad.blackjack.socket;

import com.google.common.base.MoreObjects;
import lombok.Getter;

@Getter
public class SocketRequest<T> {

    private String type;
    private T request;

    //TODO 자바 리플렉션 확인
    private SocketRequest() {}

    public SocketRequest(String type, T request) {
        this.type = type;
        this.request = request;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("type", type)
                .add("request", request)
                .toString();
    }

}
