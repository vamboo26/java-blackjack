package com.codesquad.blackjack.dto;

import com.codesquad.blackjack.domain.Chip;
import com.codesquad.blackjack.domain.Game;
import com.codesquad.blackjack.domain.player.Dealer;
import com.codesquad.blackjack.domain.player.User;
import lombok.Getter;

@Getter
public class GameDto {

    private Dealer dealer;
    private User user;
    private Chip totalBet;
    private Game.GameStatus status;
    private String winner;

    public GameDto(Dealer dealer, User user, Chip totalBet, Game.GameStatus status, String winner) {
        this.dealer = dealer;
        this.user = user;
        this.totalBet = totalBet;
        this.status = status;
        this.winner = winner;
    }

    public GameDto(Dealer dealer, User user, Chip totalBet) {
        this.dealer = dealer;
        this.user = user;
        this.totalBet = totalBet;
    }

}
