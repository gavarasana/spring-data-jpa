package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SessionJpaRepository extends JpaRepository<Session, Long> {
    List<Session> findBySessionNameContains(String sessionName);
    List<Session> findBySessionNameContainsIgnoreCase(String sessionName);
    List<Session> findBySessionLengthNot(Integer sessionLength);
    List<Session> findBySessionNameNotLike(String sessionName);
    List<Session> findBySessionNameStartingWith(String name);
    List<Session> findBySessionNameEndingWith(String name);
    List<Session> findBySessionLengthLessThan(Integer sessionLength);

    @Modifying
    @Query(
            "update Session s set s.sessionName = :sessionName where " +
                    "s.sessionId = :sessionId"
    )
    int updateSessionName(@Param("sessionId") Long sessionId, @Param("sessionName") String sessionName);
}
