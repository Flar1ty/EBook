package com.app.e_book.database;

import com.app.e_book.entities.User;
import com.app.e_book.entities.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findByUser(User user);
}
