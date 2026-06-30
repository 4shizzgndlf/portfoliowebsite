package dk.ek.portfoliowebsite.controllers;

import dk.ek.portfoliowebsite.models.Message;
import dk.ek.portfoliowebsite.models.User;
import dk.ek.portfoliowebsite.services.MessagesService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MessagesController {

    private final MessagesService service;

    public MessagesController(MessagesService service) {
        this.service = service;
    }

    @GetMapping("/messages")
    public ModelAndView messages(
            @RequestParam(required =false) Integer user,
            HttpSession session) {

        User currentUser = (User) session.getAttribute("user");

        if (currentUser == null) {
            return new ModelAndView("redirect:/login");
        }

        ModelAndView mav = new ModelAndView("messages");

        boolean isAdmin = currentUser.getEmail().equals("admin@admin.com");

        mav.addObject("isAdmin", isAdmin);

        if (isAdmin) {

            mav.addObject("users", service.getUsers().values());

            User selectedUser;

            if (user != null) {
                selectedUser = service.getUsers().get(user);
            } else {
                // pick the first non-admin user
                selectedUser = service.getUsers().values().stream()
                        .filter(u -> !u.getEmail().equals("admin@admin.com"))
                        .findFirst()
                        .orElse(null);
            }

            mav.addObject("selectedUser", selectedUser);

            if (selectedUser != null) {
                mav.addObject("messages",
                        service.getConversation(currentUser.getId(), selectedUser.getId()));
            }
        } else {

            User admin = service.getUserByEmail("admin@admin.com");

            mav.addObject("selectedUser", admin);

            mav.addObject("messages",
                    service.getConversation(currentUser.getId(), admin.getId()));
        }

        return mav;
    }

    @PostMapping("/messages/send")
    public String sendMessage(
            @RequestParam int receiverId,
            @RequestParam String content,
            HttpSession session){

        User sender = (User)session.getAttribute("user");

        service.send(new Message(
                0,
                sender.getId(),
                receiverId,
                content,
                null));

        return "redirect:/messages?user=" + receiverId;
    }
}