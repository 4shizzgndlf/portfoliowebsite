package dk.ek.portfoliowebsite.services;

import dk.ek.portfoliowebsite.models.User;
import dk.ek.portfoliowebsite.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DahsboardService {
    private final UserRepository repo;

    public DahsboardService(UserRepository repo) {
        this.repo = repo;
    }

    public Map<Integer, User> getAllUsers(){
        return repo.findAll();
    }

    public void deleteUser(int id){
        repo.delete(id);
    }
}
