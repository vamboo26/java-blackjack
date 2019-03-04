//package com.codesquad.blackjack;
//
//import com.codesquad.blackjack.domain.Game;
//import com.codesquad.blackjack.domain.card.Deck;
//import com.codesquad.blackjack.view.InputView;
//import com.codesquad.blackjack.view.OutputView;
//
//import static com.codesquad.blackjack.domain.Game.DOUBLE_SELECTION;
//import static com.codesquad.blackjack.domain.Game.HIT_SELECTION;
//import static com.codesquad.blackjack.domain.Game.STAND_SELECTION;
//
//public class BlackjackConsole {
//    public static void main(String[] args) {
//        Game game = new Game(InputView.inputPlayerName());
//        boolean nextGame = true;
//
//        while (nextGame) {
//            int bettingChip = InputView.inputBettingChip();
//            bettingChip = verifyPlayerChip(game, bettingChip);
//            Deck deck = Deck.auto();
//
//            initGame(game, bettingChip, deck);
//            playerTurnGame(game, bettingChip, deck);
//            dealerTurnGame(game, deck);
//
//            game.initializeGame();
//            nextGame = InputView.isContinue();
//
//            if(game.hasGamerNoMoney()) {
//                OutputView.printNoChip();
//                return;
//            }
//        }
//    }
//
//    private static int verifyPlayerChip(Game game, int bettingChip) {
//        while (!game.hasGamerEnoughChip(bettingChip)) {
//            bettingChip = InputView.inputBettingChip();
//        }
//
//        return bettingChip;
//    }
//
//    private static void initGame(Game game, int bettingChip, Deck deck) {
//        game.init(deck, bettingChip);
//        OutputView.printInitCards(game._toGameDto());
//
//        if (game.isBlackjack()) {
//            OutputView.printAllCardsOnTable(game._toGameDto());
//            OutputView.printEndByBlackjack(game.end(game.getBlackjackPrize()));
//        }
//    }
//
//    private static void playerTurnGame(Game game, int bettingChip, Deck deck) {
//        int turn = 0;
//        turn = selectTurn(game, bettingChip, turn);
//
//        while (game.isGameProcess() && turn != STAND_SELECTION) {
//            game.hit(deck.draw());
//            OutputView.printInitCards(game._toGameDto());
//
//            if (game.isBurst()) {
//                OutputView.printEndByBurst(game.getDealerDto(), game.getUserDto());
//                game.stopGame();
//                return;
//            }
//
//            if (game.isBlackjack() || turn == DOUBLE_SELECTION) {
//                return;
//            }
//
//            if (turn == HIT_SELECTION) {
//                turn = InputView.isHit();
//            }
//        }
//    }
//
//    private static int selectTurn(Game game, int bettingChip, int turn) {
//        if (game.isGameProcess()) {
//            turn = game.hasGamerEnoughChip(bettingChip) ? InputView.isDouble() : InputView.isHit();
//        }
//
//        return turn;
//    }
//
//    private static void dealerTurnGame(Game game, Deck deck) {
//        while (game.isGameProcess()) {
//            game.dealerTurn(deck.draw());
//
//            if (game.isBurst()) {
//                game.endByPlayerWin(game.getNormalPrize());
//                OutputView.printAllCardsOnTable(game._toGameDto());
//                OutputView.printEndByBurst(game.getUserDto(), game.getUserDto());
//                break;
//            }
//
//            OutputView.printAllCardsOnTable(game._toGameDto());
//            OutputView.printEnd(game.end(game.getNormalPrize()));
//        }
//    }
//}
