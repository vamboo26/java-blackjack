package com.codesquad.blackjack.web;

import com.codesquad.blackjack.domain.Game;
import com.codesquad.blackjack.domain.GameRepository;
import com.codesquad.blackjack.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/games")
public class GameController {
    private static final Logger log = LoggerFactory.getLogger(GameController.class);

    @Autowired
    private GameService gameService;

    @GetMapping
    public String list(Model model) {
        log.debug("*** 게임 리스트를 조회");
        model.addAttribute("games", gameService.findAll());
        return "index";
    }

    @PostMapping
    public String creat(String numberOfUser) {
        log.debug("*** {}명 게임방 생성", numberOfUser);
        Game game = gameService.create(Integer.parseInt(numberOfUser));
        return "redirect:/games/" + game.getId();
    }

    @GetMapping("/form")
    public String form() {
        log.debug("*** 게임설정 화면에 진입");
        return "/game/form";
    }



}
