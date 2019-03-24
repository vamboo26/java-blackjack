package com.codesquad.blackjack.web;

import com.codesquad.blackjack.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private static final Logger log = LoggerFactory.getLogger(GameController.class);

    @Autowired
    private GameService gameService;

    @GetMapping("/")
    public String home(Model model) {
        log.debug("*** 게임 리스트를 조회");
        model.addAttribute("games", gameService.findAll());
        return "/index";
    }
}
