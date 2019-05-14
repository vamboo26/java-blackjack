package com.codesquad.blackjack.socket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TableControllerManager {

    private final InitTableController initTableController;

    private final BettingController bettingController;

    private final DealerController dealerController;

    private final ChatController chatController;

    @Autowired
    public TableControllerManager(InitTableController initTableController, BettingController bettingController, DealerController dealerController, ChatController chatController) {
        this.initTableController = initTableController;
        this.bettingController = bettingController;
        this.dealerController = dealerController;
        this.chatController = chatController;
    }

    //TODO 이거 분명히... 개선가능할텐데? 일단 진행
    // 다형성...enum...map...?
    public TableController getTableController(String key) {
        if(key.equals("START")) {
            return initTableController;
        }

        if(key.equals("BETTING")) {
            return bettingController;
        }

        if(key.equals("DEALERTURN")) {
            return dealerController;
        }

        return chatController;
    }

}
