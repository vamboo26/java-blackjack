package com.codesquad.blackjack.domain.player;

import com.codesquad.blackjack.domain.card.Deck;

public interface DealerRole {

    void processTurn(Deck deck);

}
