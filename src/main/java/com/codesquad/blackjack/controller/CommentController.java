package com.codesquad.blackjack.controller;

import com.codesquad.blackjack.domain.player.User;
import com.codesquad.blackjack.domain.request.CommentRequest;
import com.codesquad.blackjack.security.HttpSessionUtils;
import com.codesquad.blackjack.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/posts/{postId}/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public String create(@PathVariable long postId, CommentRequest commentRequest, HttpSession session) {
        User writer = HttpSessionUtils.getUserFromSession(session);
        commentService.write(postId, commentRequest.newComment(writer));
        return "/posts/" + postId;
    }

}
