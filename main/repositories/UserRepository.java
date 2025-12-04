package repositories;

import models.User;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private static UserRepository instance;

    private UserRepository() {}

    public static UserRepository getInstance() {
        if (instance == null) instance = new UserRepository();
        return instance;
    }

    private List<User> users = new ArrayList<>();
    private int nextId = 1;

    public List<User> findAll() {
        return users;
    }

    public User findById(int id) {
        for (User u : users) {
            if (u.getUserId() == id) return u;
        }
        return null;
    }

    public User save(User user) {
        user.setUserId(nextId++);
        users.add(user);
        return user;
    }
}
