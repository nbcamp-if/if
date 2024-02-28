package com.nbcampif.ifstagram.domain.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequestDto {

  private String email;
  private String password;

}
