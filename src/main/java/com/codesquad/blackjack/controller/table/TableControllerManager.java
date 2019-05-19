package com.codesquad.blackjack.controller.table;

import com.codesquad.blackjack.domain.RequestType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import static com.codesquad.blackjack.domain.RequestType.*;

@Component
public class TableControllerManager {

    private final Map<RequestType, TableController> controllers = new HashMap<>();

    @Autowired
    private InitTableController initTableController;

    @Autowired
    private BettingController bettingController;

    @Autowired
    private DealerController dealerController;

    @Autowired
    private ChatController chatController;

    @PostConstruct
    private void init() {
        controllers.put(START, initTableController);
        controllers.put(BETTING, bettingController);
        controllers.put(DEALERTURN, dealerController);
        controllers.put(CHAT, chatController);
    }

    public TableController getTableController(RequestType type) {
        if(!controllers.containsKey(type)) {
            throw new IllegalArgumentException("key 없어오");
        }

        return controllers.get(type);
    }

}