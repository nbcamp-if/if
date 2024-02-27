package com.nbcampif.ifstagram.domain.post.dto;

import lombok.Getter;

@Getter
public class PostResponseDto {

  private final String title;
  private final String content;
  private final String postImg;

  public PostResponseDto(String title, String content, String postImg) {
    this.title = title;
    this.content = content;
    this.postImg = postImg;
  }
}
