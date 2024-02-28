package com.nbcampif.ifstagram.domain.admin.dto;

import com.nbcampif.ifstagram.domain.user.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserForceUpdateRequestDto {

  private String introduction;
  @NotBlank
  @Pattern(regexp = "^[가-힣a-zA-Z0-9]*$")
  private String nickname;
  private String profileImage;
  private UserRole role;
}
