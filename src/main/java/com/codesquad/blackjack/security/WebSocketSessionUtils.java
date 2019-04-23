package com.codesquad.blackjack.security;

import com.codesquad.blackjack.domain.player.User;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;

public class WebSocketSessionUtils {

    public static final String GAME_SESSION_KEY = "gameId";

    private static Map<String, Object> getAttributes(WebSocketSession webSocketSession) {
        return webSocketSession.getAttributes();
    }

    public static User userFromSession(WebSocketSession session) {
        Map<String, Object> httpSession = getAttributes(session);
            return (User) httpSession.get(HttpSessionUtils.USER_SESSION_KEY);
    }

    public static long gameIdFromSession(WebSocketSession session) {
        Map<String, Object> httpSession = getAttributes(session);
            return (long) httpSession.get(WebSocketSessionUtils.GAME_SESSION_KEY);
    }

}