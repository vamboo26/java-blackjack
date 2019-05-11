package com.codesquad.blackjack.socket;

public class SocketMessage<T> {

    private String type;
    private T response;

    public SocketMessage(String type, T response) {
        this.type = type;
        this.response = response;
    }

    public String getType() {
        return type;
    }

    public T getResponse() {
        return response;
    }

}
