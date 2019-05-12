package com.codesquad.blackjack.socket.socketSessionController;

import com.codesquad.blackjack.domain.Game;
import com.codesquad.blackjack.socket.GameSession;
import com.codesquad.blackjack.socket.SocketRequest;
import org.springframework.stereotype.Component;

@Component
public interface TableController {

    void handleTurn(GameSession gameSession, Game game, SocketRequest request);

}
