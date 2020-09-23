package com.pluralsight.conferencedemo.models;

import com.pluralsight.conferencedemo.repositories.SessionJpaRepository;
import com.pluralsight.conferencedemo.repositories.SessionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SessionTest {
    @Autowired
//    private SessionRepository repository;
    private SessionJpaRepository repository;

    @Test
    public void test() throws Exception {
        //List<Session> sessions = repository.getSessionsThatHaveName("Java");
        List<Session> sessions = repository.findBySessionNameContains("Java");
        assertTrue(sessions.size() > 0);
    }

    @Test
    public void testJpaNot() throws Exception{
        List<Session> sessions = repository.findBySessionLengthNot(30);
        assertTrue(sessions.size() > 0);
    }

    @Test
    public void testJpaNotLike() throws Exception {
        List<Session> sessions = repository.findBySessionNameNotLike("Java");
        assertTrue(sessions.size() > 0);
    }

    @Test
    public void testJpaStartingWith() throws Exception{
        List<Session> sessions = repository.findBySessionNameStartingWith("Spring");
        assertTrue(sessions.size() > 0);
    }

    @Test
    public void testJpaEndingWith() throws Exception {
        List<Session> sessions = repository.findBySessionNameEndingWith("Primer");
        assertTrue(sessions.size() > 0);
    }

    @Test
    public  void testJpaLessThan() throws Exception {
        List<Session> sessions = repository.findBySessionLengthLessThan(45);
        assertTrue(sessions.size() > 0);
    }
}
