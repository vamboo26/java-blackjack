package com.codesquad.blackjack.socket;

import com.codesquad.blackjack.domain.Game;
import com.codesquad.blackjack.domain.player.User;
import com.codesquad.blackjack.security.HttpSessionUtils;
import com.codesquad.blackjack.security.WebSocketSessionUtils;
import com.codesquad.blackjack.service.GameService;
import com.codesquad.blackjack.service.MessageService;
import com.codesquad.blackjack.controller.table.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.codesquad.blackjack.domain.ResponseType.JOIN;

@Component
public class BlackjackHandler extends TextWebSocketHandler {

    private static final Logger log = LoggerFactory.getLogger(BlackjackHandler.class);

    private Map<Long, GameSession> gameSessions = new HashMap<>();

    private final MessageService messageService;
    private final GameService gameService;
    private final TableControllerManager tableControllerManager;

    @Autowired
    public BlackjackHandler(MessageService messageService, GameService gameService, TableControllerManager tableControllerManager) {
        this.messageService = messageService;
        this.gameService = gameService;
        this.tableControllerManager = tableControllerManager;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        long gameId = WebSocketSessionUtils.gameIdFromSession(session);
        GameSession gameSession = findByGameId(gameId);

        //TODO 이 시점에서 방주인과 관전자를 나눠서 저장할까?
        gameSession.addSession(session);
        User user = WebSocketSessionUtils.userFromSession(session);
        messageService.sendToAll(new SocketResponse<>(JOIN, user._toChatDto()), gameSession);

        System.out.println(gameSession.getSessions().size());

        if(gameSession.getSessions().size() > 1) {
            messageService.send("관전자" ,session);
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        long gameId = getGameId(session);
        GameSession gameSession = findByGameId(gameId);
        Game game = gameService.findById(gameSession.getGameId());

        SocketRequest request = messageService.getSocketRequest(message);
        log.debug("received : '{}'", request);

        TableController controller = tableControllerManager.getTableController(request.getType());
        controller.handleTurn(gameSession, game, request);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        //TODO
        // 소켓연결 종료 후 처리
        // 관전자가 나갈 때는 어떻게 처리할까?
        long gameId = getGameId(session);
        gameSessions.remove(gameId);
    }

    private GameSession findByGameId(long gameId) {
        if (gameSessions.containsKey(gameId)) {
            return gameSessions.get(gameId);
        }
        gameSessions.put(gameId, new GameSession(gameId));
        return gameSessions.get(gameId);
    }

    private String getUserId(WebSocketSession session) {
        //addInterceptors를 통해 WebsocketSession에 httpSession이 이미 얹어진 상태에서, Map에 그 값들을 넣어준다
        //httpSession에 올라간 로그인된 유저를 불러온다
        Map<String, Object> httpSession = session.getAttributes();
        User loginUser = (User) httpSession.get(HttpSessionUtils.USER_SESSION_KEY);
        return loginUser.getUserId();
    }

    private long getGameId(WebSocketSession session) {
        Map<String, Object> httpSesion = session.getAttributes();
        return (long) httpSesion.get(WebSocketSessionUtils.GAME_SESSION_KEY);
    }

}
