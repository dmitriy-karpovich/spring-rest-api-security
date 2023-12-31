package com.company.restsecurity.dao.impl;

import com.company.restsecurity.dao.UserDao;
import com.company.restsecurity.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManager entityManager;
    private static final String FIND_BY_USERNAME = "from User where username = :username";

    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<User> findUserByUsername(String username) {

        TypedQuery<User> query = entityManager.createQuery(FIND_BY_USERNAME, User.class);
        query.setParameter("username", username);

        return query.getResultList().stream().findFirst();
    }

    @Override
    public User saveUser(User user) {
        entityManager.persist(user);
        return user;
    }
}