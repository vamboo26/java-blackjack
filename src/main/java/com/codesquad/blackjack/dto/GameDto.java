package com.codesquad.blackjack.dto;

import com.codesquad.blackjack.domain.Chip;
import com.codesquad.blackjack.domain.player.Dealer;
import com.codesquad.blackjack.domain.player.User;

public class GameDto {

    private Dealer dealer;
    private User user;
    private Chip totalBet;

    public GameDto(Dealer dealer, User user, Chip totalBet) {
        this.dealer = dealer;
        this.user = user;
        this.totalBet = totalBet;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public User getUser() {
        return user;
    }

    public Chip getTotalBet() {
        return totalBet;
    }

}
