package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    List<User> listUsers();

    User getId(long id);

    void add(User user);

    void update(long id, User updUser);

    void delete(long id);

    void cleanUsersTable();

    void addInitUsersToDB();
}
