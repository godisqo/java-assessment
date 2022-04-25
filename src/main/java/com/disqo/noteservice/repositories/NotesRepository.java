package com.disqo.noteservice.repositories;

import com.disqo.noteservice.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for NotesEntity which allows finding by Title
 */
@Repository
public interface NotesRepository extends JpaRepository<Note, Integer> {

    Note findByTitle(String title);

    List<Note> findByUserId(Integer userId);

}
