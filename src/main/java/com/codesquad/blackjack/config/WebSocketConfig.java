package com.codesquad.blackjack.config;

import com.codesquad.blackjack.socket.BlackjackHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final BlackjackHandler blackjackHandler;

    @Autowired
    public WebSocketConfig(BlackjackHandler blackjackHandler) {
        this.blackjackHandler = blackjackHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(blackjackHandler, "/game")
                .addInterceptors(new HttpSessionHandshakeInterceptor()).setAllowedOrigins("*").withSockJS();
    }

}