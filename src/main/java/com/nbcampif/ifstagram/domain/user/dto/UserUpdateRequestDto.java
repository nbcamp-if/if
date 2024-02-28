package com.nbcampif.ifstagram.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequestDto implements Serializable {

  private String introduction;
  @NotBlank
  @Pattern(regexp = "^[가-힣a-zA-Z0-9]*$")
  private String nickname;
  private String profileImage;

}
