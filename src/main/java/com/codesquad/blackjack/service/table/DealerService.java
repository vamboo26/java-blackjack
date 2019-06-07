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
import static com.codesquad.blackjack.domain.Game.GameStatus.NORMAL;
import static com.codesquad.blackjack.domain.ResponseType.INFO;

@Component
public class DealerService implements TableService {

    private final MessageService messageService;

    @Autowired
    public DealerService(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void handleRequest(GameSession gameSession, Game game, SocketRequest request) {
        game.dealerTurn();

        GameDto gameDto = (game.isBurst())
                ? game._toGameDto(BURST, game.finishGame(BURST))
                : game._toGameDto(NORMAL, game.finishGame(NORMAL));

        messageService.sendToAll(new SocketResponse<>(INFO, gameDto), gameSession);
//        userRepository.save(game.getUser());
    }

}
