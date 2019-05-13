package com.codesquad.blackjack.socket.controller;

import com.codesquad.blackjack.domain.Game;
import com.codesquad.blackjack.dto.ResultDto;
import com.codesquad.blackjack.service.MessageService;
import com.codesquad.blackjack.socket.GameSession;
import com.codesquad.blackjack.socket.SocketRequest;
import com.codesquad.blackjack.socket.SocketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.codesquad.blackjack.domain.ResponseType.*;

@Component
public class BettingController implements TableController {

    public static final int HIT_SELECTION = 1;
    public static final int STAND_SELECTION = 2;
    public static final int DOUBLE_SELECTION = 3;

    private final MessageService messageService;

    @Autowired
    public BettingController(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void handleTurn(GameSession gameSession, Game game, SocketRequest request) {
//        int turn = request.getRequest();
        int turn = 2;
        if(turn == STAND_SELECTION) {
            messageService.sendToAll(new SocketResponse<>(DEALERTURN, null), gameSession);
            return;
        }

        game.hit();
        messageService.sendToAll(new SocketResponse<>(INIT, game._toGameDto()), gameSession);

        if (game.isBurst()) {
            messageService.sendToAll(new ResultDto("BURST", "DEALER"), gameSession);
            game.initializeGame();
            return;
        }

        if (game.isBlackjack()) {
            messageService.sendToAll(new SocketResponse<>(DEALERTURN, null), gameSession);
            return;
        }

        if (turn == HIT_SELECTION) {
            messageService.sendToAll(new SocketResponse<>(BETTING, HIT_SELECTION), gameSession);
            return;
        }

        if (turn == DOUBLE_SELECTION) {
            game.setDouble();
            messageService.sendToAll(new SocketResponse<>(DEALERTURN, null), gameSession);
        }
    }

}
