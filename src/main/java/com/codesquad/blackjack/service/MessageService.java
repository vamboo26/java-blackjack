package com.codesquad.blackjack.service;

import com.codesquad.blackjack.socket.GameSession;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

@Service
public class MessageService {

    private static final Logger log = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    private ObjectMapper objectMapper;

    public <T> void sendToAll(T messageObject, GameSession gameSession) throws IOException {
        for (WebSocketSession session : gameSession.getSessions()) {
            send(messageObject, session);
        }
    }

    public <T> void send(T messageObject, WebSocketSession session) throws IOException {
        log.debug("send : {}", session);

        //TODO 예외처리, synchronized 확인
        TextMessage message = new TextMessage(objectMapper.writeValueAsString(messageObject));
        session.sendMessage(message);
    }

}
