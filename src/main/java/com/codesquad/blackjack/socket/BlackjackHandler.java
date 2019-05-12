package com.codesquad.blackjack.socket;

import com.codesquad.blackjack.domain.Game;
import com.codesquad.blackjack.domain.player.User;
import com.codesquad.blackjack.domain.player.UserRepository;
import com.codesquad.blackjack.security.HttpSessionUtils;
import com.codesquad.blackjack.security.WebSocketSessionUtils;
import com.codesquad.blackjack.service.GameService;
import com.codesquad.blackjack.service.MessageService;
import com.codesquad.blackjack.socket.socketSessionController.*;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    private final MessageService messageService;

    private final GameService gameService;

    private final ObjectMapper objectMapper;

    private final UserRepository userRepository;

    private final TableControllerManager tableControllerManager;

    @Autowired
    public BlackjackHandler(MessageService messageService, GameService gameService, ObjectMapper objectMapper, UserRepository userRepository, TableControllerManager tableControllerManager) {
        this.messageService = messageService;
        this.gameService = gameService;
        this.objectMapper = objectMapper;
        this.userRepository = userRepository;
        this.tableControllerManager = tableControllerManager;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        long gameId = WebSocketSessionUtils.gameIdFromSession(session);
        GameSession gameSession = findByGameId(gameId);

        gameSession.addSession(session);
        User user = WebSocketSessionUtils.userFromSession(session);
        messageService.sendToAll(new SocketResponse<>("JOIN", user._toChatDto()), gameSession);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        long gameId = getGameId(session);
        GameSession gameSession = findByGameId(gameId);
        Game game = gameService.findById(gameSession.getGameId());


        String payload = message.getPayload();
        log.info("payload : {}", payload);

        SocketRequest request = objectMapper.readValue(payload, SocketRequest.class);
        log.info("request : {}", request);


        TableController controller = tableControllerManager.getTableController(request.getType());
        controller.handleTurn(gameSession, game, request);

        /**
         * 1) afterConnectionEstablished
         * type : JOIN received
         * 유저 방 입장 시, 조인 메세지 채팅창 출력
         *
         * 2) start 버튼 클릭 시
         * type : START received
         * 게임 초기화
         * 배팅액, 잔여 칩 출력
         * 카드 2장씩 배분, 딜러 1장 유저 2장 출력
         */
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.debug("afterConnectionClosed : " + session + " + " + status);
        long gameId = getGameId(session);
        gameSessions.remove(gameId);
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
