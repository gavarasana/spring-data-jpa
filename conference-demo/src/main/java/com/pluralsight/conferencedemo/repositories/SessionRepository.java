package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SessionRepository {

    @Autowired
    private SessionJpaRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    public Session create(Session session) {
        return repository.saveAndFlush(session);
//        entityManager.persist(session);
//        entityManager.flush();
//        return session;
    }

    public Session update(Session session) {
        return repository.saveAndFlush(session);

//        session = entityManager.merge(session);
//        entityManager.flush();
//        return session;
    }

    public void delete(Long id) {
        repository.deleteById(id);
//        entityManager.remove(find(id));
//        entityManager.flush();
    }

    public Session find(Long id) {
        return repository.getOne(id);
        //return entityManager.find(Session.class, id);
    }

    public List<Session> list() {
        return repository.findAll();
        //return entityManager.createQuery("select s from Session s").getResultList();
    }

    public List<Session> getSessionsThatHaveName(String name) {
        List<Session> ses = entityManager
                .createQuery("select s from Session s where s.sessionName like :name")
                .setParameter("name", "%" + name + "%").getResultList();
        return ses;
    }
}
