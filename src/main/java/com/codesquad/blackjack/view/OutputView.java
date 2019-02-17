package com.codesquad.blackjack.view;

import com.codesquad.blackjack.domain.card.Card;
import com.codesquad.blackjack.domain.user.User;
import com.codesquad.blackjack.dto.CardsDto;
import com.codesquad.blackjack.dto.UserDto;

import java.util.Optional;

import static com.codesquad.blackjack.domain.Game.TIE;

public class OutputView {

    public static void printInitCards(CardsDto dealerCards, CardsDto playerCards) {
        System.out.println("*** 딜러의 카드 : " + dealerCards.getFirst());
        System.out.println("*** 플레이어의 카드 : " + playerCards);
        System.out.println("*** 플레이어의 합산결과 : " + playerCards.getTotal());
    }

    public static void printAllCardsOnTable(CardsDto dealerCards, CardsDto playerCards) {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("*** 딜러의 카드 : " + dealerCards);
        System.out.println("*** 딜러의 합산결과 : " + dealerCards.getTotal());
        System.out.println("*** 플레이어의 카드 : " + playerCards);
        System.out.println("*** 플레이어의 합산결과 : " + playerCards.getTotal());
        System.out.println("------------------------------------------------------------------------------------------------------");
    }

    public static void printEndByBlackjack(Object endByBlackjack) {
        if(endByBlackjack.equals("무승부")) {
            System.out.println("*** 무승부입니다(블랙잭)");
            return;
        }

        System.out.println("*** 블랙잭으로 " + endByBlackjack + "의 승리입니다.");
    }

    public static void printEnd(Object end) {
        if(end.equals(TIE)) {
            System.out.println("*** 무승부입니다");
            return;
        }

        System.out.println("*** 합산결과 " + end + "의 승리입니다.");
    }

    public static void printEndByBurst(UserDto burstWinner, UserDto playerDto) {
        System.out.println("*** 버스트로 " + burstWinner.getName() + "의 승리입니다.");
        System.out.println("*** 현재 플레이어의 보유 칩 : " + playerDto);
    }

    public static void printNoChip() {
        System.out.println("*** 베팅 할 칩이 없습니다. 게임을 종료합니다.");
    }
}
