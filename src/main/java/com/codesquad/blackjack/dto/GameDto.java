package com.codesquad.blackjack.dto;

import com.codesquad.blackjack.domain.Chip;

public class GameDto {
    private DealerDto dealer;
    private UserDto player;
    private Chip totalBet;

    public GameDto(DealerDto dealer, UserDto player, Chip totalBet) {
        this.dealer = dealer;
        this.player = player;
        this.totalBet = totalBet;
    }

    public DealerDto getDealer() {
        return dealer;
    }

    public UserDto getPlayer() {
        return player;
    }
}
