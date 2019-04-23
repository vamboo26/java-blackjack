package com.codesquad.blackjack.service;

import com.codesquad.blackjack.domain.Game;
import com.codesquad.blackjack.domain.GameRepository;
import com.codesquad.blackjack.domain.player.User;
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
        log.debug("*** gameService.findTop10(), 전체 게임을 반환해준다");
        return gameRepository.findAll();
    }

    public Game create(User loginUser) {
        return gameRepository.save(new Game(loginUser));
    }

    public Game remove(long gameId) {
        return gameRepository.remove(gameId);
    }

    public Game findById(long id) {
        return gameRepository.findById(id);
    }

    public void initializeGame() {

    }

}
