package com.pluralsight.conferencedemo.models;

import com.pluralsight.conferencedemo.repositories.SpeakerJpaRepository;
import com.pluralsight.conferencedemo.repositories.SpeakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SpeakerTest {

    @Autowired
    private SpeakerJpaRepository repository;

    //@PersistenceContext
   // private EntityManager entityManager;

    @Test
    public void testFind() throws Exception {
        Speaker speaker = repository.getOne(1L);
        assertNotNull(speaker);
    }

    @Test
    @Transactional
    public void testSaveAndGetAndDelete() throws Exception {
        Speaker s = new Speaker();
        s.setCompany("Pluralsight");
        s.setFirstName("Dan");
        s.setLastName("Bunker");
        s.setTitle("Author");
        s.setSpeakerBio("Consulting and mentoring");
        //s = repository.create(s);
        s = repository.saveAndFlush(s);

        // clear the persistence context so we don't return the previously cached location object
        // this is a test only thing and normally doesn't need to be done in prod code
        //entityManager.clear();

        //Speaker otherSpeaker = repository.find(s.getSpeakerId());
        Speaker otherSpeaker = repository.getOne(s.getSpeakerId());
        assertEquals("Dan", otherSpeaker.getFirstName());

        //repository.delete(otherSpeaker.getSpeakerId());
        repository.deleteById(otherSpeaker.getSpeakerId());
    }

    @Test
    private void testJpaAnd(){
        List<Speaker> speakers = repository.findByFirstNameAndLastName("Justin", "Clark");
        assertTrue(speakers.size() > 0);;
    }

    @Test
    public void testJpaOr(){
        List<Speaker> speakers = repository.findByFirstNameOrLastName("Justin", "Clark");
        assertTrue(speakers.size() > 0);
    }

    @Test
    public void testJpaIsNull(){
        List<Speaker> speakers = repository.findBySpeakerPhotoIsNull();
        assertTrue(speakers.size() > 0);
    }

    @Test
    public void testJpaIn(){
        List<String> companies = new ArrayList<>();
        companies.add("National Bank");
        companies.add("Contoso");
        List<Speaker> speakers = repository.findByCompanyIn(companies);
        assertTrue(speakers.size() > 0);
    }
    
    @Test
    public void testJpaOrderBy(){
        List<Speaker> speakers = repository.findByFirstNameOrderByLastNameAsc("James");
        speakers.forEach(speaker -> System.out.println(speaker.getLastName()));
        assertEquals("Curtis", speakers.get(0).getLastName());
    }

    @Test
    public void testJpaFirst(){
        Speaker speaker = repository.findFirstByFirstName("James");
        assertEquals("Lowrey", speaker.getLastName());
        assertTrue(speaker.getFirstName().equals("James"));
    }
}
