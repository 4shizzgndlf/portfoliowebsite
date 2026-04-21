package dk.ek.portfoliowebsite.services;

import dk.ek.portfoliowebsite.models.User;
import dk.ek.portfoliowebsite.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class IndexService {
    private final UserRepository repo;

    public IndexService(UserRepository repo) {
        this.repo = repo;
    }

    public Map<Integer, User> getAllUsers(){
        return repo.findAll();
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
