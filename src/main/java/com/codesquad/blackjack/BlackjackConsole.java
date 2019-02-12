package com.codesquad.blackjack;

import com.codesquad.blackjack.domain.Game;
import com.codesquad.blackjack.domain.card.Deck;
import com.codesquad.blackjack.view.InputView;

public class BlackjackConsole {

    public static void main(String[] args) {
        Game game = new Game();
        boolean nextGame = true;

        while(nextGame) {
            game.play(Deck.auto());
            nextGame = InputView.isContinue();
        }
    }
}
