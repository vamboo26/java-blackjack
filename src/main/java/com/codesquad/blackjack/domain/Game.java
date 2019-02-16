package com.codesquad.blackjack.domain;

import com.codesquad.blackjack.domain.card.Card;
import com.codesquad.blackjack.domain.card.Deck;
import com.codesquad.blackjack.domain.user.User;
import com.codesquad.blackjack.dto.CardsDto;

import java.util.Optional;

public class Game {
    private User dealer = new User();
    private User player = new User();
    private Chip totalBet = Chip.of(0);

    private boolean gameProgress = true;








    public void init(Deck deck, int bettingChip) {
        this.totalBet = Chip.of(bettingChip * 2);
        drawInitCards(deck);
    }

    private void drawInitCards(Deck deck) {
        for (int i = 0; i < 2; i++) {
            dealer.receiveCard(deck.draw());
            player.receiveCard(deck.draw());
        }
    }





    public User endByBlackjack() {
        if(Rule.isTie(dealer, player)) {
            player.winPrize(totalBet.half());
            return null;
        }

        if(isPlayerWin()) {
            player.winPrize(totalBet.blackjack());
            return player;
        }

        return dealer;
    }

    public User endByPlayerWin() {
        player.winPrize(totalBet);
        return player;
    }

    public Object end() {
        if(Rule.isTie(dealer, player)) {
            player.winPrize(totalBet.half());
            return Optional.empty();
        }

        if(isPlayerWin()) {
            return endByPlayerWin();
        }

        return dealer;
    }

    public void initializeGame() {
        player.initializeCards();
        dealer.initializeCards();
        gameProgress = true;
    }





    public Card hit(Deck deck) {
        return player.receiveCard(deck.draw());
    }

    public void dealerTurn(Deck deck) {
        while(dealer.getTotal() < 17) {
            dealer.receiveCard(deck.draw());
        }
    }


    private boolean isPlayerWin() {
        return Rule.getWinner(dealer, player).equals(player);
    }

    public boolean isBlackjack() {
        return Rule.isBlackjackUser(dealer) || Rule.isBlackjackUser(player);
    }

    public boolean isPlayerBlackjack() {
        return Rule.isBlackjackUser(player);
    }

    public boolean isPlayerBurst() {
        return Rule.isBurstUser(player);
    }

    public boolean isDealerBurst() {
        return Rule.isBurstUser(dealer);
    }

    public boolean isGameProcess() {
        return gameProgress;
    }

    public void stopGame() {
        this.gameProgress = false;
    }

    public CardsDto getPlayerCards() {
        return player.getCardsDto();
    }

    public CardsDto getDealerCards() {
        return dealer.getCardsDto();
    }
}
