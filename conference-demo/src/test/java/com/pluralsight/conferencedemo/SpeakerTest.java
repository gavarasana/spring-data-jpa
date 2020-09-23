package com.pluralsight.conferencedemo;

import com.pluralsight.conferencedemo.models.Speaker;
import com.pluralsight.conferencedemo.repositories.SpeakerJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SpeakerTest {
    @Autowired
    private SpeakerJpaRepository repository;

   // @PersistenceContext
  //  private EntityManager entityManager;

    @Test
    public void testFind() {
        Speaker speaker = repository.getOne(1L);
        assertNotNull(speaker);
    }

    @Test
    @Transactional
    public void testSaveAndGetAndDelete()  {
        Speaker s = new Speaker();
        s.setCompany("Pluralsight");
        s.setFirstName("Dan");
        s.setLastName("Bunker");
        s.setTitle("Author");
        s.setSpeakerBio("Consulting and mentoring");
        s = repository.saveAndFlush(s);

        // clear the persistence context so we don't return the previously cached location object
        // this is a test only thing and normally doesn't need to be done in prod code
        //entityManager.clear();

        Speaker otherSpeaker = repository.getOne(s.getSpeakerId());
        assertEquals("Dan", otherSpeaker.getFirstName());

        repository.delete(otherSpeaker);
    }

    @Test
    public void testJpaAnd(){
        List<Speaker> speakerList = repository.findByFirstNameAndLastName("Justin", "Clark");
        assertTrue(speakerList.size() > 0);
    }

    @Test
    public void testJpaOr(){
        List<Speaker> speakerList = repository.findByFirstNameOrLastName("Justin", "Clark");
        assertTrue(speakerList.size() > 0 );
    }
}
