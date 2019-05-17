package com.codesquad.blackjack.controller;

import com.codesquad.blackjack.domain.Game;
import com.codesquad.blackjack.domain.player.User;
import com.codesquad.blackjack.security.WebSocketSessionUtils;
import com.codesquad.blackjack.security.LoginUser;
import com.codesquad.blackjack.service.GameService;
import com.codesquad.blackjack.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/games")
public class GameController {

    private static final Logger log = LoggerFactory.getLogger(GameController.class);

    private final GameService gameService;
    private final UserService userService;

    @Autowired
    public GameController(GameService gameService, UserService userService) {
        this.gameService = gameService;
        this.userService = userService;
    }

    @GetMapping
    public String list() {
        return "redirect:/";
    }

    @SendTo
    @GetMapping("/start")
    public String create(@LoginUser User loginUser) {
        Game game = gameService.create(loginUser);
        return "redirect:/games/" + game.getId();
    }

    @GetMapping("/{id}")
    public String start(@PathVariable("id") long game_id, @LoginUser User loginUser, HttpSession session, Model model) {
        log.debug("*** {}가 게임방 입장", loginUser);
        session.setAttribute(WebSocketSessionUtils.GAME_SESSION_KEY, game_id);
        model.addAttribute("game", gameService.findById(game_id));
        model.addAttribute("eachUser", loginUser);
        return "/game/room";
    }

    @GetMapping("/{id}/exit")
    public String exit(@PathVariable("id") long game_id) {
        userService.save(gameService.findById(game_id).getUser());
        gameService.remove(game_id);
        return "redirect:/games";
    }

}