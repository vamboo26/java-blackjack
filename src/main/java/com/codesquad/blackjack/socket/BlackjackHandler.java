package com.codesquad.blackjack.socket;

import com.codesquad.blackjack.domain.player.User;
import com.codesquad.blackjack.dto.ChatDto;
import com.codesquad.blackjack.security.HttpSessionUtils;
import com.codesquad.blackjack.security.WebSocketSessionUtils;
import com.codesquad.blackjack.web.SessionController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;

@Component
public class BlackjackHandler extends TextWebSocketHandler {
    private static final Logger log = LoggerFactory.getLogger(BlackjackHandler.class);

    private Map<Long, GameSession> gameSessions = new HashMap<>();

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SessionController sessionController;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.debug("afterConnectionEstablished : " + session);

        long gameId = WebSocketSessionUtils.gameIdFromSession(session);
        log.debug("겜아디 : {}", gameId);

        GameSession gameSession = findByGameId(gameId);
        log.debug("겜세션 : {}", gameSession);

        User user = WebSocketSessionUtils.userFromSession(session);
        log.debug("접속유저 : {}", user);

        sessionController.readyToGame(session, gameSession);
        sessionController.startGame(gameSession);
        log.debug("*** 입장한 유저 던져준더 : {}", user._toUserDto().toString());

        for (WebSocketSession ws : gameSession.getSessions()) {
            ws.sendMessage(new TextMessage(objectMapper.writeValueAsString(user._toChatDto("JOIN"))));

            ws.sendMessage(new TextMessage(objectMapper.writeValueAsString(user._toUserDto("INIT_GAME_INFO"))));
            ws.sendMessage(new TextMessage(objectMapper.writeValueAsString(user._toUserDto("INIT_GAME_INFO"))));
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        long gameId = getGameId(session);
        GameSession gameSession = findByGameId(gameId);

        String payload = message.getPayload();
        log.info("payload : {}", payload);

        ChatDto receivedChat = objectMapper.readValue(payload, ChatDto.class);
        TextMessage chatToSend = new TextMessage(objectMapper.writeValueAsString(receivedChat));
        for (WebSocketSession gameSessionSession : gameSession.getSessions()) {
            gameSessionSession.sendMessage(chatToSend);
        }

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.debug("afterConnectionClosed : " + session + " + " + status);

        // 나중에 게임 끊기면 방 사라지게하는 로직 필요. 현재는 끊어져도 방이 남아있고 들어가도 재접속 안됨.

    }

    public GameSession findByGameId(long gameId) {
        if (gameSessions.containsKey(gameId)) {
            return gameSessions.get(gameId);
        }
        gameSessions.put(gameId, new GameSession(gameId));
        return gameSessions.get(gameId);
    }

    private String getUserId(WebSocketSession session) {
        //addInterceptors를 통해 WebsocketSession에 httpSession이 이미 얹어진 상태에서, Map에 그 값들을 넣어준다
        Map<String, Object> httpSession = session.getAttributes();
        //httpSession에 올라간 로그인된 유저를 불러온다
        User loginUser = (User) httpSession.get(HttpSessionUtils.USER_SESSION_KEY);

        return loginUser.getUserId();
    }

    private long getGameId(WebSocketSession session) {
        Map<String, Object> httpSesion = session.getAttributes();

        return (long) httpSesion.get(WebSocketSessionUtils.GAME_SESSION_KEY);
    }
}
