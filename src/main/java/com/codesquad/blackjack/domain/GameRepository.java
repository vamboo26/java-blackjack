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

    public List<Game> findAll() {
        return new ArrayList<>(games.values());
    }

//    public List<IndianPoker> findByGameStatus(GameStatus gameStatus) {
//        return indianPokerMap.values().stream()
//                .filter(i -> i.isGameStatus(gameStatus))
//                .collect(Collectors.toList());
//    }
//
//    public Optional<IndianPoker> findById(long indianPoker_id) {
//        return Optional.ofNullable(indianPokerMap.get(indianPoker_id));
//    }
//
//    public IndianPoker remove(Long gameId) {
//        return indianPokerMap.remove(gameId);
//    }
}
