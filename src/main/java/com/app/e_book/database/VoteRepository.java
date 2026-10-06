package com.app.e_book.database;

import com.app.e_book.entities.Post;
import com.app.e_book.entities.User;
import com.app.e_book.entities.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findByUser(User user);
    Optional<Vote> findByUserAndPost(User user, Post post);
    @Modifying
    @Transactional
    @Query("UPDATE Vote v SET v.upVote = :des WHERE v.id = :id")
    int updateVote(@Param("id") Long id, @Param("des") boolean des);
}
