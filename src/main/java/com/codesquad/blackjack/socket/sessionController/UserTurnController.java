package com.codesquad.blackjack.socket.sessionController;

import com.codesquad.blackjack.domain.Game;
import com.codesquad.blackjack.dto.BettingDto;
import com.codesquad.blackjack.dto.UserTurnDto;
import com.codesquad.blackjack.service.MessageService;
import com.codesquad.blackjack.socket.GameSession;

import java.io.IOException;

public class UserTurnController implements TableController {

    @Override
    public void handleTableRequestMessage(GameSession gameSession, Game game, MessageService messageService, String value) throws IOException {
        if(game.hasGamerEnoughChip(100)) {
            messageService.sendToAll(new UserTurnDto("USERTURN"), gameSession);
            messageService.sendToAll(new BettingDto("DOUBLE"), gameSession);
            return;
        }

        messageService.sendToAll(new BettingDto("NONE_DOUBLE"), gameSession);
    }

}
