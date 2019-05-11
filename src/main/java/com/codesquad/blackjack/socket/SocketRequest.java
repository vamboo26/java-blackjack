package com.codesquad.blackjack.socket;

public class SocketRequest<T> {

    private String type;
    private T request;

    public SocketRequest(String type, T request) {
        this.type = type;
        this.request = request;
    }

    public String getType() {
        return type;
    }

    public T getRequest() {
        return request;
    }

}
