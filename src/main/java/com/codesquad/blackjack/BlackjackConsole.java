package com.codesquad.blackjack;

import com.codesquad.blackjack.domain.Game;
import com.codesquad.blackjack.domain.card.Deck;
import com.codesquad.blackjack.view.InputView;
import com.codesquad.blackjack.view.OutputView;

public class BlackjackConsole {

    public static void main(String[] args) {
        Game game = new Game();
        boolean nextGame = true;

        while(nextGame) {
            int bettingChip = InputView.inputBettingChip();
            Deck deck = Deck.auto();
            boolean flag = true;

            game.play(deck, bettingChip);
            OutputView.printInitCards(game.getDealerInitCard(), game.getPlayerCards());

            if(game.isBlackjack()) {
                OutputView.printAllCardsOnTable(game.getDealerCards(), game.getPlayerCards());
                OutputView.printEndByBlackjack(game.endByBlackjack());
                flag = false;
            }

            boolean hit = true;
            while(hit && flag) {
                hit = InputView.isHit();

                if(hit) {
                    game.hit(deck);
                    OutputView.printInitCards(game.getDealerInitCard(), game.getPlayerCards());
                }

                if(game.isPlayerBurst()) {
                    OutputView.printEndByBurst();

                    flag = false;
                }

            }

            while(flag) {
                game.dealerTurn(deck);

                if(game.isDealerBurst()) {
                    game.endByDealerBurst();
                    OutputView.printAllCardsOnTable(game.getDealerCards(), game.getPlayerCards());
                    OutputView.printEndByDealerBurst();
                    break;
                }

                OutputView.printAllCardsOnTable(game.getDealerCards(), game.getPlayerCards());
                OutputView.printEnd(game.end());

                flag = false;
            }

            game.initializeGame();
            nextGame = InputView.isContinue();
        }
    }
}
