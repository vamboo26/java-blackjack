package com.codesquad.blackjack.domain;

public class Chip {
    private int amount;

    private Chip(int amount) {
        this.amount = amount;
    }

    public static Chip of(int amount) {
        return new Chip(amount);
    }
}
