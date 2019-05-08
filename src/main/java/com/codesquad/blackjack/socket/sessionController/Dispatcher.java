package com.codesquad.blackjack.socket.sessionController;

import java.util.HashMap;
import java.util.Map;

public class Dispatcher {

    private static Map<String, TableController> controllerMap = new HashMap<>();

    static {
        controllerMap.put("START", new StartTableController());
        controllerMap.put("USERTURN", new UserTurnController());
    }

    public static TableController getController(String key) {
        return controllerMap.get(key);
    }

}
