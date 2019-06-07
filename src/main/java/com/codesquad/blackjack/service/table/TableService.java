package com.codesquad.blackjack.service.table;

import com.codesquad.blackjack.domain.Game;
import com.codesquad.blackjack.socket.GameSession;
import com.codesquad.blackjack.socket.SocketRequest;
import org.springframework.stereotype.Component;

@Component
public interface TableService {

    void handleRequest(GameSession gameSession, Game game, SocketRequest request);

}
