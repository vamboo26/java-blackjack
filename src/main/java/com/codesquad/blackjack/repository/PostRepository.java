package com.codesquad.blackjack.repository;

import com.codesquad.blackjack.domain.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {

}