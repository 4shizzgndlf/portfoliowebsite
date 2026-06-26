package dk.ek.portfoliowebsite.controllers;

import dk.ek.portfoliowebsite.models.User;
import dk.ek.portfoliowebsite.services.DahsboardService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class DashboardController {
    private final DahsboardService service;

    public DashboardController(DahsboardService service) {
        this.service = service;
    }

    @GetMapping("/dashboard")
    public ModelAndView dashboard(HttpSession session) {

        User user = (User) session.getAttribute("user");

        if (user == null) {
            return new ModelAndView("redirect:/login");
        }

        if (!user.getEmail().equals("admin@admin.com")) {
            return new ModelAndView("redirect:/");
        }

        ModelAndView mav = new ModelAndView("dashboard");

        Map<Integer, User> users = service.getAllUsers();

        mav.addObject("users", users.values());
        mav.addObject("userCount", users.size());

        return mav;
    }

    @GetMapping("/deleteUser")
    public String deleteUser(
            @RequestParam int id,
            HttpSession session
    ) {

        User user = (User) session.getAttribute("user");

        if (user == null)
            return "redirect:/login";

        if (!user.getEmail().equals("admin@admin.com"))
            return "redirect:/";

        service.deleteUser(id);

        return "redirect:/dashboard";
    }
}
