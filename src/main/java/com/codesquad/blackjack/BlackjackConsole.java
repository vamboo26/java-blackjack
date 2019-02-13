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

            game.play(Deck.auto(), bettingChip);
            OutputView.printInitCards(game.getDealerInitCard(), game.getPlayerCards());

            nextGame = InputView.isContinue();

        }
    }
}
