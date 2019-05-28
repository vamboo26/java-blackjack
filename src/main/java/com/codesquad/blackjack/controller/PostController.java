package com.codesquad.blackjack.controller;

import com.codesquad.blackjack.domain.Post;
import com.codesquad.blackjack.domain.player.User;
import com.codesquad.blackjack.domain.request.PostRequest;
import com.codesquad.blackjack.security.HttpSessionUtils;
import com.codesquad.blackjack.service.PostService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/form")
    public String createForm() {
        return "/post/form";
    }

    @PostMapping("")
    @ApiOperation(value = "포스트 생성")
    public String join(@ModelAttribute PostRequest postRequest, HttpSession session) {
        User loginUser = HttpSessionUtils.getUserFromSession(session);
        postService.write(postRequest.newPost(loginUser));
        return "redirect:/posts";
    }

    @GetMapping("")
    @ApiOperation(value = "전체 포스트 조회")
    public String list(Model model) {
        Iterable<Post> posts = postService.findAll();
        model.addAttribute("posts", posts);
        return "/post/list";
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "개별 포스트 조회")
    public String read(@PathVariable long id, Model model) {
        model.addAttribute("post", postService.findById(id));
        return "/post/show";
    }

}