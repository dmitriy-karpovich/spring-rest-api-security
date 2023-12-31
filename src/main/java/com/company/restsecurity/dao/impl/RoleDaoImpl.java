package com.company.restsecurity.dao.impl;

import com.company.restsecurity.dao.RoleDao;
import com.company.restsecurity.entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class RoleDaoImpl implements RoleDao {

    private final EntityManager entityManager;

    @Autowired
    public RoleDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Role> findRoleByName(String name) {
        TypedQuery<Role> query = entityManager.createQuery("from Role where name = :roleName", Role.class);
        query.setParameter("roleName", name);
        return query.getResultList().stream().findFirst();
    }
}