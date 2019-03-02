package com.codesquad.blackjack.web;

import com.codesquad.blackjack.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    private GameService gameService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("games", gameService.findAll());
        return "index";
    }
}
