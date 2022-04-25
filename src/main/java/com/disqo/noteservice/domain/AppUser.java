package com.disqo.noteservice.domain;


import lombok.Getter;
import org.springframework.security.core.userdetails.User;

@Getter
public class AppUser extends User {

  private final Long userId;

  public AppUser(User user) {
    super(user.getUsername(), user.getPassword(), user.getAuthorities());
    this.userId = Long.parseLong(getUsername());
  }


}
