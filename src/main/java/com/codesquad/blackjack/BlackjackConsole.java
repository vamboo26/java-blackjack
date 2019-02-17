package com.codesquad.blackjack;

import com.codesquad.blackjack.domain.Game;
import com.codesquad.blackjack.domain.card.Deck;
import com.codesquad.blackjack.view.InputView;
import com.codesquad.blackjack.view.OutputView;

public class BlackjackConsole {
    public static void main(String[] args) {
        Game game = new Game(InputView.inputPlayerName());
        boolean nextGame = true;

        while(nextGame) {
            int bettingChip = InputView.inputBettingChip();

            while(!game.hasPlayerEnoughChip(bettingChip)) {
                bettingChip = InputView.inputBettingChip();
            }

            Deck deck = Deck.auto();

            initGame(game, bettingChip, deck);
            playerTurnGame(game, deck);
            dealerTurnGame(game, deck);

            game.initializeGame();
            nextGame = InputView.isContinue();
        }
    }

    private static void initGame(Game game, int bettingChip, Deck deck) {
        game.init(deck, bettingChip);
        OutputView.printInitCards(game.getDealerCards(), game.getPlayerCards());

        if(game.isBlackjack()) {
            OutputView.printAllCardsOnTable(game.getDealerCards(), game.getPlayerCards());
            OutputView.printEndByBlackjack(game.end(game.getBlackjackPrize()));
            game.stopGame();
        }
    }

    private static void playerTurnGame(Game game, Deck deck) {
        while(game.isGameProcess() && InputView.isHit()) {
            game.hit(deck);
            OutputView.printInitCards(game.getDealerCards(), game.getPlayerCards());

            if(game.isPlayerBurst()) {
                OutputView.printEndByBurst();
                game.stopGame();
            }

            if(game.isPlayerBlackjack()) {
                return;
            }
        }
    }

    private static void dealerTurnGame(Game game, Deck deck) {
        while(game.isGameProcess()) {
            game.dealerTurn(deck);

            if(game.isDealerBurst()) {
                game.endByPlayerWin(game.getNormalPrize());
                OutputView.printAllCardsOnTable(game.getDealerCards(), game.getPlayerCards());
                OutputView.printEndByDealerBurst();
                break;
            }

            OutputView.printAllCardsOnTable(game.getDealerCards(), game.getPlayerCards());
            OutputView.printEnd(game.end(game.getNormalPrize()));
            game.stopGame();
        }
    }
}
