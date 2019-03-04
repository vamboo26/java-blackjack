package com.codesquad.blackjack.domain.player;

import com.codesquad.blackjack.MessageType;
import com.codesquad.blackjack.domain.card.Card;
import com.codesquad.blackjack.dto.DealerDto;
import com.codesquad.blackjack.dto.UserDto;

public class Dealer extends AbstractPlayer {
    public static final String DEALER_NAME = "DEALER";

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public Dealer initialize() {
        return new Dealer();
    }

    public DealerDto _toDealerDto(MessageType type) {
        return new DealerDto(type, getName(), getCardsDto());
    }

    public void dealerTurn(Card card) {
        while (getCards().isDealerHit()) {
            receiveCard(card);
        }
    }

    public boolean isWinner(User target) {
        return getCards().isBigger(target.getCards());
    }

    public boolean isTie(User target) {
        return getCards().isTie(target.getCards());
    }
}