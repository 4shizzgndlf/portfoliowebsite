package dk.ek.portfoliowebsite.services;

import dk.ek.portfoliowebsite.models.Message;
import dk.ek.portfoliowebsite.models.User;
import dk.ek.portfoliowebsite.repositories.MessageRepository;
import dk.ek.portfoliowebsite.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MessagesService {

    private final MessageRepository messageRepo;
    private final UserRepository userRepo;

    public MessagesService(MessageRepository messageRepo,
                           UserRepository userRepo) {

        this.messageRepo = messageRepo;
        this.userRepo = userRepo;
    }

    public List<Message> getConversation(int sender,int receiver){

        return messageRepo.findConversation(sender,receiver);

    }

    public Map<Integer,User> getUsers(){

        return userRepo.findAll();

    }

    public void send(Message message){

        messageRepo.save(message);

    }

    public User getUserByEmail(String email) {

        for (User user : getUsers().values()) {

            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }

        }

        return null;
    }

}