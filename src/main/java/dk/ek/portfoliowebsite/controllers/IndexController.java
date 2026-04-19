package dk.ek.portfoliowebsite.controllers;

import dk.ek.portfoliowebsite.services.IndexService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    private final IndexService service;

    public IndexController(IndexService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ModelAndView index(HttpSession session){
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }
}
