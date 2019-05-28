package com.codesquad.blackjack.service;

import com.codesquad.blackjack.domain.Post;
import com.codesquad.blackjack.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;


@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post write(Post post) {
        return postRepository.save(post);
    }

    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findById(long id) {
        return postRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

}