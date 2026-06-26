package dk.ek.portfoliowebsite.controllers;

import dk.ek.portfoliowebsite.models.User;
import dk.ek.portfoliowebsite.services.ProfileService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileController {

    private final ProfileService service;

    public ProfileController(ProfileService service) {
        this.service = service;
    }

    @GetMapping("/profile")
    public ModelAndView profile(HttpSession session) {

        User sessionUser = (User) session.getAttribute("user");

        if (sessionUser == null) {
            return new ModelAndView("redirect:/login");
        }

        User user = service.getUserByEmail(sessionUser.getEmail());

        ModelAndView mav = new ModelAndView("profile");
        mav.addObject("user", user);

        return mav;
    }

    @PostMapping("/profile")
    public String updateProfile(
            @RequestParam int id,
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String firstName,
            @RequestParam String lastName,
            HttpSession session
    ) {

        User existingUser = (User) session.getAttribute("user");

        User updatedUser = new User(
                id,
                username,
                email,
                password,
                firstName,
                lastName,
                existingUser.getCreated_at()
        );

        service.updateUser(updatedUser);

        session.setAttribute("user", updatedUser);

        return "redirect:/profile";
    }
}