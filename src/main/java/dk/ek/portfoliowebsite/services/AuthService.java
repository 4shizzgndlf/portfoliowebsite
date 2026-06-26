package dk.ek.portfoliowebsite.services;

import dk.ek.portfoliowebsite.models.User;
import dk.ek.portfoliowebsite.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository repo;

    public AuthService(UserRepository repo) {
        this.repo = repo;
    }

    public User login(String email, String password) {
        User user = repo.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }

    public void register(User user) {
        repo.save(user);
    }
}
