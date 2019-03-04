package com.codesquad.blackjack.web;

import com.codesquad.blackjack.MessageType;
import com.codesquad.blackjack.domain.Game;
import com.codesquad.blackjack.domain.card.Deck;
import com.codesquad.blackjack.domain.player.User;
import com.codesquad.blackjack.dto.ResultDto;
import com.codesquad.blackjack.security.WebSocketSessionUtils;
import com.codesquad.blackjack.service.GameService;
import com.codesquad.blackjack.socket.GameSession;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

import static com.codesquad.blackjack.MessageType.BLACKJACK;
import static com.codesquad.blackjack.MessageType.INIT;

@Component
public class SessionController {
    private static final Logger log = LoggerFactory.getLogger(SessionController.class);

    @Autowired
    private GameService gameService;

    @Autowired
    private ObjectMapper objectMapper;

    public void readyToGame(WebSocketSession webSocketSession, GameSession gameSession) throws IOException {
        gameSession.addSession(webSocketSession);

        User user = WebSocketSessionUtils.userFromSession(webSocketSession);
        for (WebSocketSession ws : gameSession.getSessions()) {
            ws.sendMessage(new TextMessage(objectMapper.writeValueAsString(user._toChatDto("JOIN"))));
        }
    }

    public void startGame(WebSocketSession webSocketSession, GameSession gameSession) throws IOException {
        Game game = gameService.findById(gameSession.getGameId());
        Deck deck = Deck.auto();

        game.init(deck, 100);

        for (WebSocketSession ws : gameSession.getSessions()) {
            ws.sendMessage(new TextMessage(objectMapper.writeValueAsString(game.getDealerDto(INIT))));
            ws.sendMessage(new TextMessage(objectMapper.writeValueAsString(game.getUserDto(INIT))));
        }

        if (game.isBlackjack()) {
            for (WebSocketSession ws : gameSession.getSessions()) {
                ws.sendMessage(new TextMessage(objectMapper
                        .writeValueAsString(new ResultDto(BLACKJACK, game.end(game.getBlackjackPrize())))));
            }

            game.initializeGame();
        }
        game.initializeGame();






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