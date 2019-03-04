package com.codesquad.blackjack;

import com.codesquad.blackjack.socket.BlackjackHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new BlackjackHandler(), "/ws/chat")
                .addInterceptors(new HttpSessionHandshakeInterceptor()).setAllowedOrigins("*").withSockJS();
    }
}

