package com.codesquad.blackjack.service.table;

import com.codesquad.blackjack.domain.RequestType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.codesquad.blackjack.domain.RequestType.*;

@Component
public class TableServiceManager {

    private final Map<RequestType, TableService> services = new HashMap<>();

    @Autowired
    public TableServiceManager(InitTableService initTableController, BettingService bettingController, DealerService dealerController, ChatService chatController) {
        services.put(START, initTableController);
        services.put(BETTING, bettingController);
        services.put(DEALERTURN, dealerController);
        services.put(CHAT, chatController);
    }

    public TableService getTableService(RequestType type) {
        return services.get(type);
    }

}