package com.codesquad.blackjack.service;

import com.codesquad.blackjack.domain.Game;
import com.codesquad.blackjack.domain.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private static final Logger log = LoggerFactory.getLogger(GameService.class);

    @Autowired
    private GameRepository gameRepository;

    public Iterable<Game> findAll() {
        log.debug("*** gameService.findAll(), 전체 게임을 반환해준다");
        return gameRepository.findAll();
    }

    public Game create(int limitOfUser) {
        return gameRepository.save(new Game(limitOfUser));
    }
}
