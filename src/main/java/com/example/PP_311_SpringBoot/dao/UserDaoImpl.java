package com.example.PP_311_SpringBoot.dao;

import com.example.PP_311_SpringBoot.model.User;
import org.springframework.stereotype.Repository;
import javax.persistence.*;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    public UserDaoImpl(){}


    @Override
    public void save(User user) {
        em.persist(user);
    }

    @Override
    public List<User> index() {
        TypedQuery<User> query = em
                .createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    public User show(Long id) {
        TypedQuery<User> query = em
                .createQuery("select u from User u WHERE u.id =:id", User.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void update(User updatedUser) {
        em.merge(updatedUser);
    }

    @Override
    public void deleteById(Long id) {
        User user = em.find(User.class, id);
        if(user != null) {
            em.remove(user);
        }
    }
}
