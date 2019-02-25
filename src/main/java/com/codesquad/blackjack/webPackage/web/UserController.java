package com.codesquad.blackjack.webPackage.web;

import com.codesquad.blackjack.webPackage.domain.User;
import com.codesquad.blackjack.webPackage.security.HttpSessionUtils;
import com.codesquad.blackjack.webPackage.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Resource(name = "userService")
    private UserService userService;

    @GetMapping("/join")
    public String joinForm() {
        return "player/form";
    }

    @PostMapping("")
    public String create(User user) {
        userService.add(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "player/login";
    }

    @PostMapping("/login")
    public String login(HttpSession session, String userId, String password, Model model) {
        try {
            User user = userService.login(userId, password);
            session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);
            log.info("userId : " + userId + ", password : " + password);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "아이디 또는 비밀번호가 틀립니다. 다시 로그인 해주세요.");
            return "player/login";
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);
        return "redirect:/";
    }
}
