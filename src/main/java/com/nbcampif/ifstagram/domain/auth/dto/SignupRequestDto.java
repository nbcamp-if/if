package com.nbcampif.ifstagram.domain.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequestDto {

  @Pattern(regexp = "^[가-힣a-zA-Z0-9]{4,10}$")
  private String nickname;

  @Email
  private String email;

  @Pattern(regexp = "^[a-zA-Z0-9]{8,15}$")
  private String password;

  private String confirmPassword;

  public boolean isConfirmPasswordCorrect() {
    return Objects.equals(password, confirmPassword);
  }

}
