package com.codesquad.blackjack.socket.controller;

import com.codesquad.blackjack.domain.Game;
import com.codesquad.blackjack.dto.GameDto;
import com.codesquad.blackjack.service.MessageService;
import com.codesquad.blackjack.socket.GameSession;
import com.codesquad.blackjack.socket.SocketRequest;
import com.codesquad.blackjack.socket.SocketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.codesquad.blackjack.domain.Game.GameStatus.BLACKJACK;
import static com.codesquad.blackjack.domain.ResponseType.*;

@Component
public class InitTableController implements TableController {

    public static final int DOUBLE = 1;
    public static final int WITHOUT_DOUBLE = 2;

    private final MessageService messageService;

    @Autowired
    public InitTableController(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void handleTurn(GameSession gameSession, Game game, SocketRequest request) {
//        int bettingChip = request.getRequest();
        game.initializeGame();
        game.init(100);

        if (game.isBlackjack()) {
            GameDto gameDto = game._toGameDto(BLACKJACK, game.finishGame(BLACKJACK));
            messageService.sendToAll(new SocketResponse<>(INIT, gameDto), gameSession);
//            messageService.sendToAll(new ResultDto("BLACKJACK", game.end(game.getBlackjackPrize())), gameSession);
//            userRepository.save(game.getUser());
            return;
        }

        messageService.sendToAll(new SocketResponse<>(INIT, game._toGameDto()), gameSession);

        SocketResponse selection = (game.hasGamerEnoughChip(100))
                        ? new SocketResponse<>(SELECTION, DOUBLE)
                        : new SocketResponse<>(SELECTION, WITHOUT_DOUBLE);

        messageService.sendToAll(selection, gameSession);
    }

}
