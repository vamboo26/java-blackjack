package com.codesquad.blackjack.webPackage.web;

import com.codesquad.blackjack.webPackage.domain.User;
import com.codesquad.blackjack.webPackage.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Resource(name = "userService")
    private UserService userService;

    @GetMapping("/join")
    public String joinForm() {
        return "service/form";
    }

    @PostMapping("")
    public String create(User user) {
        userService.add(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "service/login";
    }
}
