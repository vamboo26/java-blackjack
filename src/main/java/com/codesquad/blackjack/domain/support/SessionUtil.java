package com.codesquad.blackjack.domain.support;

import com.codesquad.blackjack.domain.player.User;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;

public class SessionUtil {
    public static final String PLAYER_SESSION = "loginedUser";
    public static final String GAME_ID = "gameId";

    public static Optional<User> getUserFromSession(NativeWebRequest webRequest) {
        return Optional.ofNullable((User) webRequest.getAttribute(PLAYER_SESSION, webRequest.SCOPE_SESSION));
    }

}