package com.codesquad.blackjack.socket;

import com.codesquad.blackjack.domain.Game;
import com.codesquad.blackjack.domain.player.User;
import com.codesquad.blackjack.domain.player.UserRepository;
import com.codesquad.blackjack.dto.*;
import com.codesquad.blackjack.security.HttpSessionUtils;
import com.codesquad.blackjack.security.WebSocketSessionUtils;
import com.codesquad.blackjack.service.GameService;
import com.codesquad.blackjack.service.MessageService;
import com.codesquad.blackjack.web.SessionController;
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

import static com.codesquad.blackjack.domain.Game.*;
import static com.codesquad.blackjack.domain.MessageType.INIT;

@Component
public class BlackjackHandler extends TextWebSocketHandler {

    private static final Logger log = LoggerFactory.getLogger(BlackjackHandler.class);

    private Map<Long, GameSession> gameSessions = new HashMap<>();

    private final MessageService messageService;

    private final GameService gameService;

    private final SessionController sessionController;

    private final ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public BlackjackHandler(MessageService messageService, GameService gameService, SessionController sessionController, ObjectMapper objectMapper) {
        this.messageService = messageService;
        this.gameService = gameService;
        this.sessionController = sessionController;
        this.objectMapper = objectMapper;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        long gameId = WebSocketSessionUtils.gameIdFromSession(session);
        GameSession gameSession = findByGameId(gameId);

        gameSession.addSession(session);
        User user = WebSocketSessionUtils.userFromSession(session);
        messageService.sendToAll(user._toChatDto("JOIN"), gameSession);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        long gameId = getGameId(session);
        GameSession gameSession = findByGameId(gameId);
        Game game = gameService.findById(gameSession.getGameId());

        String payload = message.getPayload();
        log.info("payload : {}", payload);

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
         *
         *
         *
         */

        if(payload.contains("START")) {
            game.initializeGame();
            game.init(100); //칩 선택하게 하고 싶은데 일단 고정으로 해보자
            //베팅칩, 유저잔여칩 보여주기

            if (game.isBlackjack()) {
                //둘다 블랙잭이면 무승부
                //아니면 블랙잭이 승자, 게임 끝

                //게임끝내고, 상금배분로직
                //종료결과, 승자, 잔여칩 보여주기
                //둘의 카드 다 보여주기
                //버튼 모두 숨기기
                messageService.sendToAll(new SocketMessage<>("INIT", game._toGameDto()), gameSession);

//                messageService.sendToAll(game.getDealerDto(INIT), gameSession);
//                messageService.sendToAll(game.getUserDto(INIT), gameSession);
                messageService.sendToAll(new ResultDto("BLACKJACK", game.end(game.getBlackjackPrize())), gameSession);
                userRepository.save(game.getUser());
                return;
            }

            //블랙잭 없으면
            //딜러 1장, 유저 2장 보여주기
            //버튼 열어준다(보유칩에 따라 더블다운까지)


            messageService.sendToAll(new SocketMessage<>("INIT", game._toGameDto()), gameSession);



            if(game.hasGamerEnoughChip(100)) {
                messageService.sendToAll(new BettingDto("DOUBLE"), gameSession);
            } else {
                messageService.sendToAll(new BettingDto("NONE_DOUBLE"), gameSession);
            }
            //여기까지했으면 버튼 잘 열어주고 끝났어
            //이제 버튼 누르길 기다려야함
        }

//        if(payload.contains("USERTURN")) {
//            log.debug("USERTURN 실행");
//            sessionController.playerTurnGame(gameSession, 100);
//            return;
//        }

        if(payload.contains("BETTING")) {
            //버튼에 따라서 숫자를 parsing해서 가질수있음
            int turn = Integer.parseInt(payload.split(":")[1]);

            //스탠드 아닌이상 무조건 한장더뽑아
            if (turn != STAND_SELECTION) {
                game.hit();

                //한장 추가했으니 유저의 갱신된카드 뿌려줘
                messageService.sendToAll(game.getUserDto(INIT), gameSession);

                //히트 또눌렀어, 이 메소드 다시 호출해야해
                if (turn == HIT_SELECTION) {
                    messageService.sendToAll(new BettingDto("NONE_DOUBLE"), gameSession);
                    return;
                }

                //뽑았더니 버스트됐어, 그럼 무조건 딜러승
                //딜러 승 처리해야해
                //종료결과, 승자, 잔여칩 보여줘
                //유저를 여기서도 저장해줘야할까?
                if (game.isBurst()) {
                    messageService.sendToAll(new ResultDto("BURST", "DEALER"), gameSession);

                    game.initializeGame();
                    return;
                }

                //뽑았더니 21됐어, 그럼 바로 딜러턴으로 넘어가
                //버튼 숨기고 딜러턴 수행해
                if (game.isBlackjack()) {
                    messageService.sendToAll(new DealerTurnDto("DELAERTURN"), gameSession);
                    return;
                }

                //더블을 눌렀어(블랙잭/버스트 안됨) 그럼 바로 딜러턴으로 넘어가
                if (turn == DOUBLE_SELECTION) {
                    game.setDouble();
                    messageService.sendToAll(new DealerTurnDto("DELAERTURN"), gameSession);
                    return;
                }
            }

            //딜러턴 수행해(버튼숨겨야해)
            messageService.sendToAll(new DealerTurnDto("DEALERTURN"), gameSession);
        }

        if(payload.contains("DEALERTURN")) {
            log.debug("DEALERTURN 실행");

            //룰에 따라 카드 뽑든 안뽑든해
            game.dealerTurn();


            //딜러 버스트나버렸어
            if(game.isBurst()) {
                game.endByPlayerWin(game.getNormalPrize());
                messageService.sendToAll(new ResultDto("BURST", "USER"), gameSession);
            } else {
                //노말하게 드로우가 끝났어
                messageService.sendToAll(new ResultDto("NORMAL", game.end(game.getNormalPrize())), gameSession);
            }

            //모든 카드 뿌려주고
            //종료결과, 승자, 잔여칩 보여줘
            //유저 저장해줘

            messageService.sendToAll(game.getDealerDto(INIT), gameSession);
            messageService.sendToAll(game.getUserDto(INIT), gameSession);
            userRepository.save(game.getUser());
            return;
        }

        //채팅
        if(payload.contains("CHAT")) {
            ChatDto receivedChat = objectMapper.readValue(payload, ChatDto.class);
            TextMessage chatToSend = new TextMessage(objectMapper.writeValueAsString(receivedChat));
            for (WebSocketSession gameSessionSession : gameSession.getSessions()) {
                gameSessionSession.sendMessage(chatToSend);
            }
        }
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
