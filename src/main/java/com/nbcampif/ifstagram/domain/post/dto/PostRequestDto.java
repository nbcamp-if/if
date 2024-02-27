package com.nbcampif.ifstagram.domain.post.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter // test를 위해 추가했습니다.
public class PostRequestDto {

  private String title;
  private String content;
}
