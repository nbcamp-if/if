package com.nbcampif.ifstagram.domain.user.model;

import com.nbcampif.ifstagram.domain.auth.dto.SignupRequestDto;
import com.nbcampif.ifstagram.domain.user.UserRole;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;
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
  String password;
  String profileImage;
  String introduction = "";
  Long reportedCount = 0L;
  UserRole role = UserRole.USER;

  private static final String DEFAULT_PROFILE_IMAGE = "https://k.kakaocdn.net/dn/1G9kp/btsAot8liOn/8CWudi3uy07rvFNUkk3ER0/img_640x640.jpg";

  public User(
      Long userId,
      String email,
      String nickname,
      String password,
      String profileImage,
      UserRole role
  ) {
    this.userId = userId;
    this.email = email;
    this.nickname = nickname;
    this.password = password;
    this.profileImage = profileImage;
    this.role = role;
  }

  public User(String email, String nickname, String password, String profileImage) {
    this.email = email;
    this.nickname = nickname;
    this.password = password;
    this.profileImage = profileImage;
  }

  public static User ofSignUp(SignupRequestDto requestDto) {
    return new User(requestDto.getEmail(), requestDto.getNickname(), requestDto.getPassword(), DEFAULT_PROFILE_IMAGE);
  }


  public static User ofOauth2(
      String email, String nickname, String profileImage
  ) {
    return new User(email, nickname, UUID.randomUUID().toString(), profileImage);
  }

  private User(Long userId, String email, String nickname, String profileImage) {
    this.userId = userId;
    this.email = email;
    this.nickname = nickname;
    this.password = UUID.randomUUID().toString();
    this.profileImage = profileImage;
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
