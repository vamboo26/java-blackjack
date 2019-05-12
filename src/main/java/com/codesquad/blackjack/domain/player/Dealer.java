package com.codesquad.blackjack.domain.player;

import com.codesquad.blackjack.domain.ResponseType;
import com.codesquad.blackjack.dto.DealerDto;
import lombok.Getter;

@Getter
public class Dealer extends AbstractPlayer {

    public static final String DEALER_NAME = "DEALER";

    public Dealer() {
        super(DEALER_NAME);
    }

    public DealerDto _toDealerDto(ResponseType type) {
        return new DealerDto(type, getName(), getCardsDto());
    }

    public boolean dealerTurn() {
        return getCards().isDealerHit();
    }

    public boolean isWinner(User target) {
        return getCards().isBigger(target.getCards());
    }

    public boolean isTie(User target) {
        return getCards().isTie(target.getCards());
    }

}