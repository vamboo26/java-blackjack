package com.codesquad.blackjack.domain.player;

import com.codesquad.blackjack.domain.card.Card;

public interface Player {

    void initialize();

    void receiveCard(Card card);

    boolean isBurst();

    boolean isBlackjack();

}