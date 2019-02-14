package com.codesquad.blackjack.domain;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chip chip = (Chip) o;
        return amount == chip.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
