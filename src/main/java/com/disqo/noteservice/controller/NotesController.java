package com.disqo.noteservice.controller;

import com.disqo.noteservice.domain.Note;
import com.disqo.noteservice.repositories.NotesRepository;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class to handle CRUD operations on Notes.
 */
@RestController
@RequestMapping("/api/v1.0/notes")
@CommonsLog
@AllArgsConstructor
public class NotesController {

  private final NotesRepository notesRepository;

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Note> addNote(Authentication authentication,
      @Valid @RequestBody NotesRequest notesRequest) {
    User user = (User) authentication.getPrincipal();

    Note newNote = Note.builder().note(notesRequest.getNote())
        .userId(Long.parseLong(user.getUsername()))
        .title(notesRequest.getTitle()).build();
    newNote = add(newNote);
    return new ResponseEntity<Note>(newNote, HttpStatus.OK);
  }

  public Note add(Note note) {
    return notesRepository.save(note);
  }

}
