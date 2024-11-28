package web.services;

import web.models.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);
    List<User> listUsers();
    User getUser(Long id);
    void deleteUser(Long id);
    void updateUser(User user);
}
