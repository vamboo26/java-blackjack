package com.codesquad.blackjack.web;

import com.codesquad.blackjack.domain.Game;
import com.codesquad.blackjack.domain.card.Deck;
import com.codesquad.blackjack.service.GameService;
import com.codesquad.blackjack.socket.GameSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
public class SessionController {
    private static final Logger log = LoggerFactory.getLogger(SessionController.class);

    @Autowired
    private GameService gameService;

    public void readyToGame(WebSocketSession webSocketSession, GameSession gameSession) {
        gameSession.addSession(webSocketSession);
        log.debug("*** {}가 게임에 입장하여 준비완료!", webSocketSession.getId());
    }

    public void startGame(GameSession gameSession) {
        log.debug("{]번 게임방, 게임 시작합니다.", gameSession.getGameId());
        for (WebSocketSession session : gameSession.getSessions()) {
            log.debug("참여자는 {}입니다.", session.getId());
        }

        Game game = gameService.findById(gameSession.getGameId());
        boolean nextGame = true;

//        while (nextGame) {
        int bettingChip = 100;
//            bettingChip = verifyPlayerChip(game, bettingChip);
        Deck deck = Deck.auto();

        game.init(deck, bettingChip);
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
    }
}