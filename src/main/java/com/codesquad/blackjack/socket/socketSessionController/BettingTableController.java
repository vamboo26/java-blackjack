package com.codesquad.blackjack.socket.socketSessionController;

import com.codesquad.blackjack.domain.Game;
import com.codesquad.blackjack.dto.ResultDto;
import com.codesquad.blackjack.service.MessageService;
import com.codesquad.blackjack.socket.GameSession;
import com.codesquad.blackjack.socket.SocketRequest;
import com.codesquad.blackjack.socket.SocketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BettingTableController implements TableController {

    public static final int HIT_SELECTION = 1;

    public static final int STAND_SELECTION = 2;

    public static final int DOUBLE_SELECTION = 3;

    private final MessageService messageService;

    @Autowired
    public BettingTableController(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void handleTurn(GameSession gameSession, Game game, SocketRequest request) {
        //버튼에 따라서 숫자를 parsing해서 가질수있음
//        int turn = Integer.parseInt(payload.split(":")[1]);
        int turn = 2;

        //스탠드 아닌이상 무조건 한장더뽑아
        if (turn != STAND_SELECTION) {
            game.hit();

            //한장 추가했으니 유저의 갱신된카드 뿌려줘
            messageService.sendToAll(new SocketResponse<>("INIT", game._toGameDto()), gameSession);

            //히트 또눌렀어, 이 메소드 다시 호출해야해
            if (turn == HIT_SELECTION) {
                messageService.sendToAll(new SocketResponse<>("BETTING", HIT_SELECTION), gameSession);
//                messageService.sendToAll(new BettingDto("NONE_DOUBLE"), gameSession);
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
                messageService.sendToAll(new SocketResponse<>("DEALERTURN", null), gameSession);
                return;
            }

            //더블을 눌렀어(블랙잭/버스트 안됨) 그럼 바로 딜러턴으로 넘어가
            if (turn == DOUBLE_SELECTION) {
                game.setDouble();
                messageService.sendToAll(new SocketResponse<>("DEALERTURN", null), gameSession);
                return;
            }
        }

        //딜러턴 수행해(버튼숨겨야해)
        messageService.sendToAll(new SocketResponse<>("DEALERTURN", null), gameSession);
    }

}
