package com.codesquad.blackjack.dto;

import com.codesquad.blackjack.domain.Chip;

public class GameDto {
    private PlayerDto dealer;
    private PlayerDto player;
    private Chip totalBet;

    public GameDto(PlayerDto dealer, PlayerDto player, Chip totalBet) {
        this.dealer = dealer;
        this.player = player;
        this.totalBet = totalBet;
    }

    public PlayerDto getDealer() {
        return dealer;
    }

    public PlayerDto getPlayer() {
        return player;
    }
}
