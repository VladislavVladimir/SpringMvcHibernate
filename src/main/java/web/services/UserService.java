package web.services;

import web.models.User;

import java.util.List;

public interface UserService {
    void saveOrUpdateUser(User user);
    List<User> listUsers();
    User getUser(Long id);
    void deleteUser(Long id);
}
