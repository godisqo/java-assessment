package com.disqo.noteservice;


import static org.assertj.core.api.Assertions.assertThat;

import com.disqo.noteservice.domain.Note;
import com.disqo.noteservice.controller.NotesRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class NoteApiTest {

  private String testUserName = "user";
  private String testUserPassword = "password";
  private final String NOTE_API_HOST  = "http://localhost:";
  private final String NOTE_API = "/api/v1.0/notes";

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void createNote_validRequest_verifyResult(){

    ResponseEntity<Note> notesRequestResponseEntity = restTemplate
        .withBasicAuth(testUserName, testUserPassword)
        .postForEntity(NOTE_API_HOST + port + NOTE_API,
            NotesRequest.builder().note("test_note").title("test_title").build(),
            Note.class);

    assertThat(notesRequestResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(notesRequestResponseEntity.getBody().getTitle()).isEqualTo("test_title");
    assertThat(notesRequestResponseEntity.getBody().getNote()).isEqualTo("test_note");

  }

  @Test
  public void createNote_withBlankTitle_verify400HttpStatus()
      throws JsonProcessingException, JSONException {

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    JSONObject requestWithEmptyTitle = new JSONObject();
    requestWithEmptyTitle.put("title", "");
    requestWithEmptyTitle.put("note", "test_note");

    HttpEntity<String> request = new HttpEntity<String>(requestWithEmptyTitle.toString(), headers);

    ResponseEntity<String> notesRequestResponseEntity = restTemplate
        .withBasicAuth(testUserName, testUserPassword)
        .postForEntity(NOTE_API_HOST + port + NOTE_API,
            request,
            String.class);

    assertThat(notesRequestResponseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

  }

}
