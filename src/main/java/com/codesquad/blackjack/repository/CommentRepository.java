package com.codesquad.blackjack.repository;

import com.codesquad.blackjack.domain.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
