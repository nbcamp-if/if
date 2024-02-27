package com.nbcampif.ifstagram.domain.user.model;

import com.nbcampif.ifstagram.domain.user.UserRole;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Getter
@AllArgsConstructor
public class User implements OAuth2User {

  Long userId;
  String email;
  String nickname;
  String profileImage;
  String introduction;
  Long reportedCount = 0L;
  UserRole role;

  public User(Long userId, String email, String nickname, String profileImage) {
    this.userId = userId;
    this.email = email;
    this.nickname = nickname;
    this.profileImage = profileImage;
    this.role = UserRole.USER;
  }

  public User(Long userId, String email, String nickname, String profileImage, UserRole role) {
    this.userId = userId;
    this.email = email;
    this.nickname = nickname;
    this.profileImage = profileImage;
    this.role = role;
  }

  @Override
  public Map<String, Object> getAttributes() {
    return Collections.emptyMap();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.emptyList();
  }

  @Override
  public String getName() {
    return this.nickname;
  }

}
