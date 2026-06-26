package dk.ek.portfoliowebsite.services;

import dk.ek.portfoliowebsite.models.User;
import dk.ek.portfoliowebsite.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProfileService {

    private final UserRepository repo;

    public ProfileService(UserRepository repo) {
        this.repo = repo;
    }

    public User getUserByEmail(String email) {
        return repo.findByEmail(email);
    }

    public void updateUser(User user) {
        repo.update(user);
    }
}
