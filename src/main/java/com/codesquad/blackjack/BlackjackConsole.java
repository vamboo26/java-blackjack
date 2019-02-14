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
            boolean gameProgress = true;
            boolean hit = true;

            gameProgress = initGame(game, bettingChip, deck, gameProgress);
            gameProgress = playerTurnGame(game, deck, gameProgress, hit);
            dealerTurnGame(game, deck, gameProgress);

            game.initializeGame();
            nextGame = InputView.isContinue();
        }
    }

    private static void dealerTurnGame(Game game, Deck deck, boolean gameProgress) {
        while(gameProgress) {
            game.dealerTurn(deck);

            if(game.isDealerBurst()) {
                game.endByDealerBurst();
                OutputView.printAllCardsOnTable(game.getDealerCards(), game.getPlayerCards());
                OutputView.printEndByDealerBurst();
                break;
            }

            OutputView.printAllCardsOnTable(game.getDealerCards(), game.getPlayerCards());
            OutputView.printEnd(game.end());
            gameProgress = false;
        }
    }

    private static boolean playerTurnGame(Game game, Deck deck, boolean gameProgress, boolean hit) {
        while(hit && gameProgress) {
            hit = InputView.isHit();

            if(hit) {
                game.hit(deck);
                OutputView.printInitCards(game.getDealerInitCard(), game.getPlayerCards());
            }

            if(game.isPlayerBurst()) {
                OutputView.printEndByBurst();
                gameProgress = false;
            }

            if(game.isPlayerBlackjack()) {
                hit = false;
            }
        }
        return gameProgress;
    }

    private static boolean initGame(Game game, int bettingChip, Deck deck, boolean gameProgress) {
        game.play(deck, bettingChip);
        OutputView.printInitCards(game.getDealerInitCard(), game.getPlayerCards());

        if(game.isBlackjack()) {
            OutputView.printAllCardsOnTable(game.getDealerCards(), game.getPlayerCards());
            OutputView.printEndByBlackjack(game.endByBlackjack());
            gameProgress = false;
        }
        return gameProgress;
    }
}
