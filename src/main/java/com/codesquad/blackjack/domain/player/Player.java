package com.codesquad.blackjack.domain.player;

import com.codesquad.blackjack.domain.card.Card;
import com.codesquad.blackjack.dto.CardsDto;

public interface Player {

    void initialize();

    void receiveCard(Card card);

    boolean isBurst();

    boolean isBlackjack();

    CardsDto getCardsDto();
}