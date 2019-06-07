package com.codesquad.blackjack.domain.card;

import com.codesquad.blackjack.domain.Suit;
import lombok.Getter;

@Getter
public class Card {

    public static final int ONE = 1;
    public static final int TEN = 10;
    public static final int ELEVEN = 11;
    public static final int TWELVE = 12;
    public static final int THIRTEEN = 13;

    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    boolean isAce() {
        return this.rank.equals(Rank.ACE);
    }

}
