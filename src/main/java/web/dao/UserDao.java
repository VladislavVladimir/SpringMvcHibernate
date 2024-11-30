package web.dao;

import web.models.User;

import java.util.List;

public interface UserDao {
    void saveOrUpdateUser(User user);
    List<User> listUsers();
    User getUser(Long id);
    void deleteUser(Long id);
}
