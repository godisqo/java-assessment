package com.disqo.noteservice.domain;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Entity class for Notes
 */
@Entity
@Table(name = "notes")
@Data
@Getter
@Builder
public class Note {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(name = "user_id")
  private long userId;
  /**
   * title cannot be blank and null with max size of note is 50 characters
   */
  @NonNull
  @NotBlank
  @Size(max = 50)
  @Column(name = "title", nullable = false)
  private String title;

  /**
   * max size of note is 1000 characters
   */
  @Size(max = 1000)
  @Column(name = "note")
  private String note;

  @Column(name = "create_time")
  @CreationTimestamp
  private Timestamp createdTime;

  @Column(name = "last_update_time")
  @UpdateTimestamp
  private Timestamp lastUpdateTime;

  public Note(Long id, Long userId, String title, String note, Timestamp createTime, Timestamp lastUpdateTime) {
    this.id = id;
    this.userId = userId;
    this.title = title;
    this.note = note;
    this.createdTime = createTime;
    this.lastUpdateTime = lastUpdateTime;
  }
}
