package services;

import models.User;
import repositories.UserRepository;

import java.util.List;

public class UserService {

    private UserRepository userRepository = UserRepository.getInstance();

    public User registerUser(String name, String email, String password) {
        User u = new User(0, name, email, password);
        return userRepository.save(u);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getById(int id) {
        return userRepository.findById(id);
    }
}

