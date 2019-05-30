package com.codesquad.blackjack.service.table;

import com.codesquad.blackjack.domain.Game;
import com.codesquad.blackjack.dto.GameDto;
import com.codesquad.blackjack.service.MessageService;
import com.codesquad.blackjack.socket.GameSession;
import com.codesquad.blackjack.socket.SocketRequest;
import com.codesquad.blackjack.socket.SocketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.codesquad.blackjack.domain.Game.GameStatus.BURST;
import static com.codesquad.blackjack.domain.ResponseType.*;

@Component
public class BettingService implements TableService {

    public static final int HIT_SELECTION = 1;
    public static final int STAND_SELECTION = 2;
    public static final int DOUBLE_SELECTION = 3;

    private final MessageService messageService;

    @Autowired
    public BettingService(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void handleTurn(GameSession gameSession, Game game, SocketRequest request) {
        int turn = (int) request.getRequest();

        if(turn == STAND_SELECTION) {
            messageService.processToDealerTurn(gameSession);
            return;
        }

        game.hit();
        messageService.sendToAll(new SocketResponse<>(INFO, game._toGameDto()), gameSession);

        if (game.isBurst()) {
            GameDto gameDto = game._toGameDto(BURST, game.finishGame(BURST));
            messageService.sendToAll(new SocketResponse<>(INFO, gameDto), gameSession);
            game.initializeGame();
            return;
        }

        if (game.isBlackjack()) {
            messageService.processToDealerTurn(gameSession);
            return;
        }

        if (turn == HIT_SELECTION) {
            messageService.sendToAll(new SocketResponse<>(BETTING, HIT_SELECTION), gameSession);
            return;
        }

        if (turn == DOUBLE_SELECTION) {
            game.raiseDouble();
            messageService.processToDealerTurn(gameSession);
        }
    }

}
