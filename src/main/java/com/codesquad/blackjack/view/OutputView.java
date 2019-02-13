package com.codesquad.blackjack.view;

import com.codesquad.blackjack.domain.card.Card;
import com.codesquad.blackjack.domain.user.User;

import java.util.List;

public class OutputView {

    public static void printInitCards(Card dealerCard, List<Card> playerCards) {
        System.out.println("*** 딜러의 카드 : " + dealerCard);
        System.out.println("*** 플레이어의 카드 : " + playerCards);
    }

    public static void printAllCardsOnTable(List<Card> dealerCards, List<Card> playerCards) {
        System.out.println("*** 딜러의 카드 : " + dealerCards);
        System.out.println("*** 플레이어의 카드 : " + playerCards);    }

    public static void printEndByBlackjack(User endByBlackjack) {
        if(endByBlackjack == null) {
            System.out.println("*** 무승부입니다(블랙잭)");
        }

        System.out.println("블랙잭으로 " + endByBlackjack + "의 승리입니다.");
    }

    public static void printCard(Card card) {
        System.out.println("*** 플레이어의 카드 : " + card);
    }

    public static void printEnd(User end) {
        if(end == null) {
            System.out.println("*** 무승부입니다");
        }

        System.out.println("합산결과 " + end + "의 승리입니다.");
    }
}
