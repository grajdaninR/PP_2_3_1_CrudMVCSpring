package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;
import web.repository.UserDao;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    public final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public void addInitUsersToDB() {
        List<User> initUsersList = new ArrayList<>();

        userDao.cleanUsersTable();
        initUsersList.add(new User("Anton","Antonov","Antonov@gmail.com","Engelsa 9"));
        initUsersList.add(new User("Ivan","Ivanov","Ivanov@gmail.com","Lenina 128"));
        initUsersList.add(new User("Maksim","Maksimov","Maksimov@gmail.com","Marksa 24"));
        initUsersList.add(new User("Andrey","Andreev","Andreev@gmail.com","Marksa 189"));
        initUsersList.add(new User("Evgeniy","Tarasov","Tarasov@gmail.com","Kurchatova 76"));
        for (User user : initUsersList) {
            userDao.add(user);
        }
    }

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User getId(long id) {
        return userDao.getId(id);
    }

    @Transactional
    @Override
    public void update(long id, User updUser) {
        userDao.update(id, updUser);
    }

    @Transactional
    @Override
    public void delete(long id) {
        userDao.delete(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Transactional
    @Override
    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }
}
