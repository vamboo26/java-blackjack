package com.codesquad.blackjack.domain.player;

import com.codesquad.blackjack.domain.card.Card;
import com.codesquad.blackjack.dto.CardsDto;
import com.codesquad.blackjack.dto.PlayerDto;

public interface Player {

    Player initialize();

    void receiveCard(Card card);

    boolean isBurst();

    boolean isBlackjack();

    CardsDto getCardsDto();

    PlayerDto _toUserDto();
}