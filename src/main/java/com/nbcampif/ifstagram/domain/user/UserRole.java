package com.nbcampif.ifstagram.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {
  ADMIN("ROLE_ADMIN"),
  USER("ROLE_USER"),
  DISABLED("ROLE_DISABLED")
  ;
  private final String authority;
}
