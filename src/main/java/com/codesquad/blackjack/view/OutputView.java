package com.codesquad.blackjack.view;

import com.codesquad.blackjack.domain.card.Card;

import java.util.List;

public class OutputView {

    public static void printInitCards(Card dealerCard, List<Card> playerCards) {
        System.out.println("*** 딜러의 카드 : " + dealerCard);
        System.out.println("*** 플레이어의 카드 : " + playerCards);
    }
}
