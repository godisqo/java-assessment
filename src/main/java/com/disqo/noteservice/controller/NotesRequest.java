package com.disqo.noteservice.controller;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * class to create request for CRUD operations on Notes
 */
@Data
@Getter
@Setter
@Builder
public class NotesRequest {

    @NotBlank
    @Size(max = 50)
    private String title;

    @Size(max = 1000)
    @Column(name = "note")
    private String note;
}
