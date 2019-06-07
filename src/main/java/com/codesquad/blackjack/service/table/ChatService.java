package com.codesquad.blackjack.service.table;

import com.codesquad.blackjack.domain.Game;
import com.codesquad.blackjack.service.MessageService;
import com.codesquad.blackjack.socket.GameSession;
import com.codesquad.blackjack.socket.SocketRequest;
import com.codesquad.blackjack.socket.SocketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.codesquad.blackjack.domain.ResponseType.CHAT;

@Component
public class ChatService implements TableService {

    private final MessageService messageService;

    @Autowired
    public ChatService(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void handleRequest(GameSession gameSession, Game game, SocketRequest request) {
        messageService.sendToAll(new SocketResponse<>(CHAT, request.getRequest()), gameSession);
    }

}
