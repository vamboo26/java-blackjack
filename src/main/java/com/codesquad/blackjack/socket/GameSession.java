package com.codesquad.blackjack.socket;

import com.google.common.base.MoreObjects;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;

public class GameSession {

    private Long gameId;
    private List<WebSocketSession> sessions;

    GameSession(Long gameId) {
        this.gameId = gameId;
        this.sessions = new ArrayList<>();
    }

    void addSession(WebSocketSession webSocketSession) {
        this.sessions.add(webSocketSession);
    }

    Long getGameId() {
        return gameId;
    }

    public List<WebSocketSession> getSessions() {
        return sessions;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("gameId", gameId)
                .add("sessions", sessions)
                .toString();
    }

}
