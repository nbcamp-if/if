package com.nbcampif.ifstagram.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nbcampif.ifstagram.domain.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDto {

  private String email;
  private String introduction;
  private String nickname;
  private String profileImage;
  private Long reportedCount;


  public static UserResponseDto of(User user) {
    return new UserResponseDto(user.getEmail(), user.getIntroduction(), user.getNickname(), user.getProfileImage(), user.getReportedCount());
  }

}
