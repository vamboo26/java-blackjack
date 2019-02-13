package com.codesquad.blackjack.domain;

public class Chip {
    private int amount;

    private Chip(int amount) {
        this.amount = amount;
    }

    public static Chip of(int amount) {
        return new Chip(amount);
    }

    public Chip half() {
        this.amount /= 2;
        return this;
    }

    public Chip sum(Chip target) {
        this.amount += target.amount;
        return this;
    }

    public Chip blackjack() {
        this.amount *= 1.5;
        return this;
    }
}
