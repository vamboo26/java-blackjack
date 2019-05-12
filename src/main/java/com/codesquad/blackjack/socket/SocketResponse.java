package com.codesquad.blackjack.socket;

import com.codesquad.blackjack.domain.ResponseType;
import com.google.common.base.MoreObjects;
import lombok.Getter;

@Getter
public class SocketResponse<T> {

    private ResponseType type;
    private T response;

    public SocketResponse(ResponseType type, T response) {
        this.type = type;
        this.response = response;
    }

    //TODO Guava Objects vs. MoreObjects 차이는? 전자 deprecated
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("type", type)
                .add("response", response)
                .toString();
    }

}
