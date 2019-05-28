package com.codesquad.blackjack.repository;

import com.codesquad.blackjack.domain.player.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUserId(String userId);

    List<User> findTop10ByOrderByChipAmountDesc();

    long countByChipAmountGreaterThanEqual(int amount);

}