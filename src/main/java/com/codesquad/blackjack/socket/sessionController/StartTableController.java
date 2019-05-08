package com.codesquad.blackjack.socket.sessionController;

import com.codesquad.blackjack.domain.Game;
import com.codesquad.blackjack.dto.UserTurnDto;
import com.codesquad.blackjack.service.MessageService;
import com.codesquad.blackjack.socket.GameSession;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StartTableController implements TableController {

    @Override
    public void handleTableRequestMessage(GameSession gameSession, Game game, MessageService messageService, String value) throws IOException {
        game.init(100);

        messageService.sendToAll("딜러 카드1장, 유저 카드 2장을 보여줍니다", gameSession);

        if (game.isBlackjack()) {
            messageService.sendToAll("블랙잭으로 게임 끝났습니다. 승자와 잔여칩도 보여주면 좋을 듯, 레이즈 버튼은 숨겨야합니다", gameSession);
            game.initializeGame();
            return;
        }

        messageService.sendToAll(new UserTurnDto("USERTURN"), gameSession);
    }

}
