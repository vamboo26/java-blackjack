package com.codesquad.blackjack.service;

import com.codesquad.blackjack.domain.Comment;
import com.codesquad.blackjack.domain.Post;
import com.codesquad.blackjack.repository.CommentRepository;
import com.codesquad.blackjack.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public Comment write(long postId, Comment comment) {
        Post post = postRepository.findById(postId).orElseThrow(NoSuchElementException::new);
        post.write(comment);
        comment.toPost(post);
        return commentRepository.save(comment);
    }

}
