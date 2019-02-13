package com.codesquad.blackjack;

import com.codesquad.blackjack.domain.Game;
import com.codesquad.blackjack.domain.card.Deck;
import com.codesquad.blackjack.view.InputView;
import com.codesquad.blackjack.view.OutputView;
import org.springframework.data.domain.PageRequest;

public class BlackjackConsole {

    public static void main(String[] args) {
        Game game = new Game();
        boolean nextGame = true;

        while(nextGame) {
            int bettingChip = InputView.inputBettingChip();
            Deck deck = Deck.auto();

            game.play(deck, bettingChip);
            OutputView.printInitCards(game.getDealerInitCard(), game.getPlayerCards());

            if(game.isBlackjack()) {
                OutputView.printAllCardsOnTable(game.getDealerCards(), game.getPlayerCards());
                OutputView.printEndByBlackjack(game.endByBlackjack());
            }

            boolean hit = true;
            while(hit) {
                hit = InputView.isHit();

                if(hit) {
                    game.hit(deck);
                    OutputView.printInitCards(game.getDealerInitCard(), game.getPlayerCards());
                }
            }

            game.dealerTurn(deck);
            OutputView.printAllCardsOnTable(game.getDealerCards(), game.getPlayerCards());

            OutputView.printEnd(game.end());

            game.initializeGame();
            nextGame = InputView.isContinue();

        }
    }
}
