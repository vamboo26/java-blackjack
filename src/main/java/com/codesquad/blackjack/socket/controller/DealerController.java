package com.codesquad.blackjack.socket.controller;

import com.codesquad.blackjack.domain.Game;
import com.codesquad.blackjack.dto.GameDto;
import com.codesquad.blackjack.service.MessageService;
import com.codesquad.blackjack.socket.GameSession;
import com.codesquad.blackjack.socket.SocketRequest;
import com.codesquad.blackjack.socket.SocketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.codesquad.blackjack.domain.GameStatus.BURST;
import static com.codesquad.blackjack.domain.GameStatus.NORMAL;
import static com.codesquad.blackjack.domain.ResponseType.INIT;

@Component
public class DealerController implements TableController {

    private final MessageService messageService;

    @Autowired
    public DealerController(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void handleTurn(GameSession gameSession, Game game, SocketRequest request) {
        game.dealerTurn();

        if(game.isBurst()) {
//            game.endByPlayerWin(game.getNormalPrize());
            GameDto gameDto = game._toGameDto(BURST, game.finishGame(BURST));
            messageService.sendToAll(new SocketResponse<>(INIT, gameDto), gameSession);
//            messageService.sendToAll(new ResultDto("BURST", "USER"), gameSession);
        } else {
            GameDto gameDto = game._toGameDto(NORMAL, game.finishGame(NORMAL));
            messageService.sendToAll(new SocketResponse<>(INIT, gameDto), gameSession);
//            messageService.sendToAll(new ResultDto("NORMAL", game.end(game.getNormalPrize())), gameSession);
        }

//        messageService.sendToAll(new SocketResponse<>(INIT, game._toGameDto()), gameSession);
//        userRepository.save(game.getUser());
    }

}
