package com.codesquad.blackjack.socket;

import com.codesquad.blackjack.domain.RequestType;
import com.google.common.base.MoreObjects;
import lombok.Getter;

@Getter
public class SocketRequest<T> {

    private RequestType type;
    private T request;

    //TODO 자바 리플렉션 확인
    private SocketRequest() {}

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("type", type)
                .add("request", request)
                .toString();
    }

}
