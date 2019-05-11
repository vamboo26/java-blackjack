package com.codesquad.blackjack.web;

import com.codesquad.blackjack.domain.Game;
import com.codesquad.blackjack.domain.player.User;
import com.codesquad.blackjack.domain.player.UserRepository;
import com.codesquad.blackjack.dto.BettingDto;
import com.codesquad.blackjack.dto.DealerTurnDto;
import com.codesquad.blackjack.dto.ResultDto;
import com.codesquad.blackjack.dto.UserTurnDto;
import com.codesquad.blackjack.security.WebSocketSessionUtils;
import com.codesquad.blackjack.service.GameService;
import com.codesquad.blackjack.service.MessageService;
import com.codesquad.blackjack.socket.GameSession;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

import static com.codesquad.blackjack.domain.MessageType.INIT;
import static com.codesquad.blackjack.domain.Game.DOUBLE_SELECTION;
import static com.codesquad.blackjack.domain.Game.HIT_SELECTION;
import static com.codesquad.blackjack.domain.Game.STAND_SELECTION;


@Component
public class SessionController {

    private static final Logger log = LoggerFactory.getLogger(SessionController.class);

    private final GameService gameService;

    private final ObjectMapper objectMapper;

    private final UserRepository userRepository;

    private final MessageService messageService;

    @Autowired
    public SessionController(GameService gameService, ObjectMapper objectMapper, UserRepository userRepository, MessageService messageService) {
        this.gameService = gameService;
        this.objectMapper = objectMapper;
        this.userRepository = userRepository;
        this.messageService = messageService;
    }

    public void readyToGame(WebSocketSession webSocketSession, GameSession gameSession) throws IOException {
        gameSession.addSession(webSocketSession);

        User user = WebSocketSessionUtils.userFromSession(webSocketSession);
        messageService.sendToAll(user._toChatDto("JOIN"), gameSession);
    }

    public void startGame(GameSession gameSession) throws IOException {
//        Game game = gameService.findById(gameSession.getGameId());
//        game.initializeGame();
//
//        game.init(100);
//
//        System.out.println("*** 게임 시작합니닷!!!");
//
//        if (game.isBlackjack()) {
//            messageService.sendToAll(new ResultDto("BLACKJACK", game.end(game.getBlackjackPrize())), gameSession);
//            userRepository.save(game.getUser());
//            return;
//        }
//
//        messageService.sendToAll(game.getDealerDto(INIT), gameSession);
//        messageService.sendToAll(game.getUserDto(INIT), gameSession);
//
//        messageService.sendToAll(new UserTurnDto("USERTURN"), gameSession);
    }

    public void playerTurnGame(GameSession gameSession, int bettingChip) throws IOException {
        Game game = gameService.findById(gameSession.getGameId());

        if(game.hasGamerEnoughChip(bettingChip)) {
            messageService.sendToAll(new BettingDto("DOUBLE"), gameSession);
            return;
        }

        messageService.sendToAll(new BettingDto("NONE_DOUBLE"), gameSession);
    }

    public void playerSelect(GameSession gameSession, int turn) throws IOException {
        Game game = gameService.findById(gameSession.getGameId());

//        if (turn != STAND_SELECTION) {
//            game.hit();
//
//            messageService.sendToAll(game.getDealerDto(INIT), gameSession);
//            messageService.sendToAll(game.getUserDto(INIT), gameSession);
//
//
//            if (game.isBurst()) {
//                messageService.sendToAll(new ResultDto("BURST", "DEALER"), gameSession);
//
//                game.initializeGame();
//                return;
//            }
//
//            if (game.isBlackjack()) {
//                messageService.sendToAll(new DealerTurnDto("DELAERTURN"), gameSession);
//                return;
//            }
//
//            if (turn == DOUBLE_SELECTION) {
//                game.setDouble();
//                messageService.sendToAll(new DealerTurnDto("DELAERTURN"), gameSession);
//                return;
//            }
//
//            if (turn == HIT_SELECTION) {
//                messageService.sendToAll(new BettingDto("NONE_DOUBLE"), gameSession);
//                return;
//            }
//        }
//
//        messageService.sendToAll(new DealerTurnDto("DEALERTURN"), gameSession);
    }

    public void dealerTurnGame(GameSession gameSession) throws IOException {
//        Game game = gameService.findById(gameSession.getGameId());
//
//        game.dealerTurn();
//
//        if (game.isBurst()) {
//            game.endByPlayerWin(game.getNormalPrize());
//
//            messageService.sendToAll(new ResultDto("BURST", "USER"), gameSession);
//            game.initializeGame();
//            userRepository.save(game.getUser());
//            return;
//        }
//
//        messageService.sendToAll(game.getDealerDto(INIT), gameSession);
//        messageService.sendToAll(game.getUserDto(INIT), gameSession);
//        messageService.sendToAll(new ResultDto("NORMAL", game.end(game.getNormalPrize())), gameSession);
//        userRepository.save(game.getUser());
    }

}