package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionJpaRepository extends JpaRepository<Session, Long> {
    List<Session> findBySessionNameContains(String sessionName);
<<<<<<< HEAD
=======
    List<Session> findBySessionLengthNot(Integer sessionLength);
    List<Session> findBySessionNameNotLike(String sessionName);
    List<Session> findBySessionNameStartingWith(String name);
    List<Session> findBySessionNameEndingWith(String name);
    List<Session> findBySessionLengthLessThan(Integer sessionLength);
>>>>>>> 9e3b738895e290fe9a55feb14383661643f61bf0
}
