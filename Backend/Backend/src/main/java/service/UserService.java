package service;

import model.Patient;
import model.User;
import model.UserRole;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserService {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<UserRole> getUserRoleByUser(String username){
        Query query = sessionFactory.getCurrentSession().createQuery("from UserRole where user.name=:username");
        query.setString("username", username);
        return query.list();
    }

    public List<User> findUsersByName(String name){
        Query query = sessionFactory.getCurrentSession().createQuery("from User u where u.name like :name order by id ASC");

        query.setString("name", name);

        return query.list();
    }

    //READ Method: Get all users
    public List<User> getAllUsers(){
        Query query = sessionFactory.getCurrentSession().createQuery("from User order by id ASC");
        return query.list();
    }
}
