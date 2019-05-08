package com.codesquad.blackjack.socket.sessionController;

import com.codesquad.blackjack.domain.Game;
import com.codesquad.blackjack.service.MessageService;
import com.codesquad.blackjack.socket.GameSession;

import java.io.IOException;

public interface TableController {

    void handleTableRequestMessage(GameSession gameSession, Game game, MessageService messageService, String value) throws IOException;

}
