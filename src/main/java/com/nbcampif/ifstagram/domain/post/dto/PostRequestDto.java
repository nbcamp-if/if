package com.nbcampif.ifstagram.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostRequestDto {

  private String title;
  private String content;
}
