package com.codesquad.blackjack.domain;

import java.util.Objects;

public class Chip {

    public static final int ZERO = 0;
    public static final int TWO = 2;
    public static final double ONE_POINT_FIVE = 1.5;

    private final int amount;

    public Chip(int amount) {
        this.amount = amount;
    }

    public Chip sum(Chip target) {
        return new Chip(amount + target.amount);
    }

    public Chip subtract(int bettingChip) {
        return new Chip(amount - bettingChip);
    }

    public Chip blackjack() {
        return new Chip((int)(amount + (amount * ONE_POINT_FIVE)));
    }

    public Chip twice() {
        return new Chip(amount * TWO);
    }

    public boolean isOver(int bettingChip) {
        return this.amount >= bettingChip;
    }

    public boolean isZero() {
        return this.amount <= ZERO;
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

    @Override
    public String toString() {
        return "Chip{" +
                "amount=" + amount +
                '}';
    }
}
