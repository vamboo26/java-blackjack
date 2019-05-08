package com.codesquad.blackjack.socket;

import com.codesquad.blackjack.service.MessageService;

import java.io.IOException;

public class StartTableController implements TableController {

    @Override
    public void handleTableRequestMessage(GameSession gameSession, int value, MessageService messageService) throws IOException {
        System.out.println("여기까진잘오나요??");
        System.out.println(gameSession);
        messageService.sendToAll("hi", gameSession);
    }

}
