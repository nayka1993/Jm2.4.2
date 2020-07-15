package web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import web.model.User;

import javax.persistence.Query;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {


    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void addUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(user);
    }

    @Override
    public void updateUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        return (List<User>) session.createQuery("from User").list();
    }

    @Override
    public User getUserById(int id) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("from User where id =:id")
                .setParameter("id",id);
        return (User) query.getSingleResult();
    }

    @Override
    public void removeUser(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = session.load(User.class, id);
        if(null != user){
            session.delete(user);
        }
    }

    @Override
    @Transactional
    public User getUserByName(String name) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("from User where name =:name")
                .setParameter("name",name);
        return (User) query.getSingleResult();
    }

}
