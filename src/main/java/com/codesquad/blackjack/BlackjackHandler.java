package com.codesquad.blackjack;

import com.codesquad.blackjack.domain.player.User;
import com.codesquad.blackjack.domain.support.SessionUtil;
import com.codesquad.blackjack.socket.GameSession;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;

public class BlackjackHandler extends TextWebSocketHandler {
    private static final Logger log = LoggerFactory.getLogger(BlackjackHandler.class);
    
    Map<Long, GameSession> gameSessions = new HashMap<>();
    Map<String, WebSocketSession> userSessions = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.debug("afterConnectionEstablished : " + session);

        long gameId = getGameId(session);
        // 새로 추가되는 방이면 게임세션을 생성하고, 이미 존재했으면 게임세션에 사용자만 추가한다
        if(!gameSessions.containsKey(gameId)) {
            GameSession gameSession = new GameSession(gameId);
            gameSession.addSession(session);
            gameSessions.put(gameId, gameSession);
        } else {
            gameSessions.get(gameId).addSession(session);
        }

        String senderId = getUserId(session);
        userSessions.put(senderId, session);

        for (WebSocketSession ws : gameSessions.get(gameId).getSessions()) {
            ws.sendMessage(new TextMessage(senderId + "님이 접속하셨습니다"));
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.debug("handleTextMessage : " + session + " + " + message);

        long gameId = getGameId(session);

        String senderId = getUserId(session);
        String msg = message.getPayload();

        if (StringUtils.isNotEmpty(msg)) {
            for (WebSocketSession ws : gameSessions.get(gameId).getSessions()) {
                ws.sendMessage(new TextMessage(senderId + " : " + msg));
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.debug("afterConnectionClosed : " + session + " + " + status);

        // 나중에 게임 끊기면 방 사라지게하는 로직 필요. 현재는 끊어져도 방이 남아있고 들어가도 재접속 안됨.

    }

    private String getUserId(WebSocketSession session) {
        //addInterceptors를 통해 WebsocketSession에 httpSession이 이미 얹어진 상태에서, Map에 그 값들을 넣어준다
        Map<String, Object> httpSession = session.getAttributes();
        //httpSession에 올라간 로그인된 유저를 불러온다
        User loginUser = (User) httpSession.get(SessionUtil.PLAYER_SESSION);

        return loginUser.getUserId();
    }

    private long getGameId(WebSocketSession session) {
        Map<String, Object> httpSesion = session.getAttributes();

        return (long) httpSesion.get(SessionUtil.GAME_ID);
    }
}
