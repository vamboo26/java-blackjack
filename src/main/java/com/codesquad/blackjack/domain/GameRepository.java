package com.codesquad.blackjack.domain;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class GameRepository {
    private Map<Long, Game> games;
    private long id = 0;

    public GameRepository() {
        this.games = new HashMap<>();
    }

    public Game save(Game game) {
        game.setId(++this.id);
        this.games.put(this.id, game);
        return this.games.get(this.id);
    }

    public Game remove(long gameId) {
        return games.remove(gameId);
    }

    public List<Game> findAll() {
        return new ArrayList<>(games.values());
    }

    public Game findById(long id) {
        return games.get(id);
    }
}
