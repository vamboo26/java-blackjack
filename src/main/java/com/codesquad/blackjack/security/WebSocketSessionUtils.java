package com.codesquad.blackjack.security;

import com.codesquad.blackjack.domain.player.User;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;

import static com.codesquad.blackjack.security.HttpSessionUtils.USER_SESSION_KEY;

public class WebSocketSessionUtils {
    public static final String GAME_SESSION_KEY = "gameId";

    public static Optional<User> getUserFromSession(NativeWebRequest webRequest) {
        return Optional.ofNullable((User) webRequest.getAttribute(USER_SESSION_KEY, webRequest.SCOPE_SESSION));
    }
}