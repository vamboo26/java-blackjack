package com.codesquad.blackjack.domain;

import com.codesquad.blackjack.domain.card.Deck;
import com.codesquad.blackjack.domain.player.Dealer;
import com.codesquad.blackjack.domain.player.User;
import com.codesquad.blackjack.dto.DealerDto;
import com.codesquad.blackjack.dto.GameDto;
import com.codesquad.blackjack.dto.UserDto;

public class Game {



    private Dealer dealer = new Dealer();
    private User user;
    private Chip totalBet = new Chip(0);
    private Deck deck = Deck.auto();

    private long id;

    public Game(String playerName) {
        this.user = new User(playerName);
    }

    public Game(User loginUser) {
        this.user = loginUser;
    }

    public void init(int bettingChip) {
        this.totalBet = new Chip(bettingChip);
        this.user.betChip(bettingChip);
        drawInitCards(this.deck);
    }

    private void drawInitCards(Deck deck) {
        for (int i = 0; i < 2; i++) {
            dealer.receiveCard(deck.draw());
            user.receiveCard(deck.draw());
        }
    }

    public void initializeGame() {
        user.initialize();
        dealer.initialize();
        deck = Deck.auto();
    }

    public String end(Chip prize) {

        if(!dealer.isWinner(user)) {
            return endByPlayerWin(prize);
        }

        return dealer.isTie(user) ? endByTie() : "DEALER";
    }

    private String endByTie() {
        user.winPrize(totalBet);
        return "TIE";
    }

    public String endByPlayerWin(Chip prize) {
        user.winPrize(prize);
        return "USER";
    }

    public Chip getBlackjackPrize() {
        return totalBet.blackjack();
    }

    public Chip getNormalPrize() {
        return totalBet.twice();
    }

    public void hit() {
        user.receiveCard(this.deck.draw());
    }

    public void dealerTurn() {
        while(dealer.dealerTurn()) dealer.receiveCard(this.deck.draw());
    }

    public boolean isBlackjack() {
        return dealer.isBlackjack() || user.isBlackjack();
    }

    public boolean isBurst() {
        return dealer.isBurst() || user.isBurst();
    }

    public boolean hasGamerEnoughChip(int bettingChip) {
        return user.checkChip(bettingChip);
    }

    public boolean hasGamerNoMoney() {
        return user.isBankruptcy();
    }

    public UserDto getUserDto(MessageType type) {
        return user._toUserDto(type);
    }

    public DealerDto getDealerDto(MessageType type) {
        return dealer._toDealerDto(type);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public User getUser() {
        return user;
    }

    public GameDto _toGameDto() {
        return new GameDto(dealer, user, totalBet);
    }

    public void setDouble() {
        this.user.betChip(totalBet.getAmount());
        this.totalBet = totalBet.twice();
    }

}
