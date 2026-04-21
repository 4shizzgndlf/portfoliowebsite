package dk.ek.portfoliowebsite.controllers;

import dk.ek.portfoliowebsite.models.User;
import dk.ek.portfoliowebsite.services.IndexService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final IndexService service;

    public AuthController(IndexService service) {
        this.service = service;
    }

    // LOGIN PAGE
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // LOGIN ACTION
    @PostMapping("/login")
    public String login(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session
    ) {
        User user = service.login(email, password);

        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/";
        }

        return "login"; // failed login
    }

    // REGISTER PAGE
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    // REGISTER ACTION
    @PostMapping("/register")
    public String register(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String firstName,
            @RequestParam String lastName
    ) {
        User user = new User(0, username, email, password, firstName, lastName, null);
        service.register(user);

        return "redirect:/login";
    }

    // LOGOUT
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}