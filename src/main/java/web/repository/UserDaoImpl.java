package web.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public UserDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    @Override
    public void add(User user) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    @Override
    public User getId(long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        User user = em.find(User.class, id);

        em.getTransaction().commit();
        return user;
    }

    @Override
    public void update(long id, User updUser) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        User userToBeUpdated = em.find(User.class, id);
        userToBeUpdated.setFirstName(updUser.getFirstName());
        userToBeUpdated.setLastName(updUser.getLastName());
        userToBeUpdated.setEmail(updUser.getEmail());
        userToBeUpdated.setAddress(updUser.getAddress());

        em.getTransaction().commit();
    }

    @Override
    public void delete(long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        User usr = em.find(User.class, id);
        em.remove(usr);

        em.getTransaction().commit();
    }

    @Override
    public List<User> listUsers() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<User> query = em.createQuery("SELECT user from User user", User.class);

        em.getTransaction().commit();
        return query.getResultList();
    }

    @Override
    public void cleanUsersTable() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        em.createQuery("DELETE FROM User").executeUpdate();

        em.getTransaction().commit();
    }
}
