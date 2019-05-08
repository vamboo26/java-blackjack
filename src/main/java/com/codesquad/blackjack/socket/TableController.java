package com.codesquad.blackjack.socket;

import com.codesquad.blackjack.service.MessageService;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public interface TableController {

    void handleTableRequestMessage(GameSession gameSession, int value, MessageService messageService) throws IOException;

}
