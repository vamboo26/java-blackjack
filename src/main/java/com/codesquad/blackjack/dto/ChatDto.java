package com.codesquad.blackjack.dto;

public class ChatDto {
    private String type;
    private String userName;
    private String message;

    public ChatDto() {
    }

    public ChatDto(String type, String userName, String message) {
        this.type = type;
        this.userName = userName;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ChatDto{" +
                "type='" + type + '\'' +
                ", userName='" + userName + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
