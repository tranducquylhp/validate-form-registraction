package com.codegym.repository.impl;

import com.codegym.model.User;
import com.codegym.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class UserRepositoryImpl implements UserRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> findAll() {
        TypedQuery<User> query = em.createQuery("select c from User c", User.class);
        return query.getResultList();
    }

    @Override
    public void save(User user) {
        if(findById(user.getId()) != null){
            em.merge(user);
        } else {
            em.persist(user);
        }
    }

    public User findById(Long id) {
        TypedQuery<User> query = em.createQuery("select c from User c where  c.id=:id", User.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }
}
