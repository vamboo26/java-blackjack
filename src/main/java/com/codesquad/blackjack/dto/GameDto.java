package com.codesquad.blackjack.dto;

import com.codesquad.blackjack.domain.Chip;

public class GameDto {
    private UserDto dealer;
    private UserDto player;
    private Chip totalBet;

    public GameDto(UserDto dealer, UserDto player, Chip totalBet) {
        this.dealer = dealer;
        this.player = player;
        this.totalBet = totalBet;
    }

    public UserDto getDealer() {
        return dealer;
    }

    public UserDto getPlayer() {
        return player;
    }
}
