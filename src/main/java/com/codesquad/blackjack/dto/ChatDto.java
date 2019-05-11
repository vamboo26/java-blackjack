package com.codesquad.blackjack.dto;

import lombok.Getter;

@Getter
public class ChatDto {

    private String userName;
    private String message;

    public ChatDto() {

    }

    public ChatDto(String userName) {
        this.userName = userName;
    }

    public ChatDto(String userName, String message) {
        this.userName = userName;
        this.message = message;
    }

}
