package com.codesquad.blackjack.socket;

import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;

public class GameSession {

    private Long gameId;
    private List<WebSocketSession> sessions;

    public GameSession(Long gameId) {
        this.gameId = gameId;
        this.sessions = new ArrayList<>();
    }

    public void addSession(WebSocketSession webSocketSession) {
        this.sessions.add(webSocketSession);
    }

    public Long getGameId() {
        return gameId;
    }

    public List<WebSocketSession> getSessions() {
        return sessions;
    }

}
