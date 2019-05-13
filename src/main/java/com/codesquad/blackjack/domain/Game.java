package com.codesquad.blackjack.domain;

import com.codesquad.blackjack.domain.card.Deck;
import com.codesquad.blackjack.domain.player.Dealer;
import com.codesquad.blackjack.domain.player.User;
import com.codesquad.blackjack.dto.GameDto;

import static com.codesquad.blackjack.domain.GameStatus.*;

public class Game {

    private long id;
    //TODO dealer, user Pair 객체로 분리 고민
    private Dealer dealer = new Dealer();
    private User user;
    private Deck deck;
    private Chip totalBet;

    public Game(User loginUser) {
        this.user = loginUser;
    }

    public void initializeGame() {
        user.initialize();
        dealer.initialize();
        deck = Deck.auto();
        totalBet = new Chip(0);
    }

    public void init(int bettingChip) {
        //TODO exception 정리필요
        if(hasGamerNoMoney()) {
            throw new IllegalArgumentException("칩이 없어요");
        }

        totalBet = new Chip(bettingChip);
        user.betChip(bettingChip);
        drawInitCards(this.deck);
    }

    private void drawInitCards(Deck deck) {
        dealer.receiveCard(deck.draw());
        dealer.receiveCard(deck.draw());
        user.receiveCard(deck.draw());
        user.receiveCard(deck.draw());
    }


    /**
     * dealer, user 둘다 블랙잭 -> 무승부(push)
     * user의 버스트 -> dealer 상관없이 dealer 승리
     * dealer만 버스트 -> 유저 승리 (베팅액 * 2)
     * 두 케이스 제외하면 모두 normal 숫자계산(둘다 21보다 작거나 같은 상황에서 크기 비교)
     *
     * 1. INIT
     * 둘다블랙잭 -> 무승부 종료
     * 딜러블랙잭 -> 딜러 승리
     * 유저블랙잭 -> 유저 승리((베팅액 * 2) * 1.5
     *
     * 2. 유저턴
     * 유저버스트 -> 딜러 승리
     * 나머지 -> 딜러턴으로
     *
     * 3. 딜러턴
     * 딜러버스트 -> 유저 승리 (베팅액 * 2)
     * 나머지 -> 합 비교 후 유저 승리 시 (베팅액 * 2)
     */

    public String finishGame(GameStatus status) {
        if(status.equals(BLACKJACK)) {
            if(isBothBlackjack()) {
                return "TIE";
            }

            if(!dealer.isWinner(user)) {
                user.winPrize(totalBet.blackjack());
                return "USER";
            }

            return "DEALER";
        }

        if(status.equals(BURST)) {
            if(user.isBurst()) {
                return "DEALER";
            }

            user.winPrize(totalBet.twice());
            return "USER";
        }

        if(!dealer.isWinner(user)) {
            user.winPrize(totalBet.twice());
            return "USER";
        }

        if(!dealer.isTie(user)) {
            return "DEALER";
        }

        return "TIE";
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

    public boolean isBothBlackjack() {
        return dealer.isBlackjack() && user.isBlackjack();
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void raiseDouble() {
        this.user.betChip(totalBet.getAmount());
        this.totalBet = totalBet.twice();
    }

    public GameDto _toGameDto() {
        return new GameDto(dealer, user, totalBet);
    }

    public GameDto _toGameDto(GameStatus status, String winner) {
        return new GameDto(dealer, user, totalBet, status, winner);
    }

}
