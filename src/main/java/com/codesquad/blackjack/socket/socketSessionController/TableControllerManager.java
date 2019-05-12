package com.codesquad.blackjack.socket.socketSessionController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TableControllerManager {

    private final InitTableController initTableController;

    private final BettingTableController bettingTableController;

    private final DealerTableController dealerTableController;

    private final ChatController chatController;

    @Autowired
    public TableControllerManager(InitTableController initTableController, BettingTableController bettingTableController, DealerTableController dealerTableController, ChatController chatController) {
        this.initTableController = initTableController;
        this.bettingTableController = bettingTableController;
        this.dealerTableController = dealerTableController;
        this.chatController = chatController;
    }

    //TODO 이거 분명히... 개선가능할텐데? 일단 진행
    public TableController getTableController(String key) {
        if(key.equals("START")) {
            return initTableController;
        }

        if(key.contains("BETTING")) {
            return bettingTableController;
        }

        if(key.equals("DEALERTURN")) {
            return dealerTableController;
        }

        return chatController;
    }

}
