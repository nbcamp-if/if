package com.nbcampif.ifstagram.domain.post.dto;

import lombok.Getter;

@Getter
public class PostResponseDto {
  private String title;
  private String content;
  private String postImg;

  public PostResponseDto (String title, String content, String postImg){
    this.title = title;
    this.content = content;
    this.postImg = postImg;
  }
}
