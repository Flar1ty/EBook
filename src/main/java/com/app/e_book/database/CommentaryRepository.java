package com.app.e_book.database;

import com.app.e_book.entities.Commentary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentaryRepository extends JpaRepository<Commentary, UUID> {

}
