package com.nbcampif.ifstagram.domain.repost.dto;

import lombok.Getter;

@Getter
public class RepostResponseDto {
  private String title;
  private String content;
  private String postImg;

  public RepostResponseDto(String title, String content, String postImg) {
    this.title = title;
    this.content = content;
    this.postImg = postImg;
  }
}
