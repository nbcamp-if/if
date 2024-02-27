package com.nbcampif.ifstagram.domain.repost.dto;

import lombok.Getter;

@Getter
public class RepostResponseDto {

  private final String title;
  private final String content;
//  private String postImg;

  public RepostResponseDto(String title, String content, String postImg) {
    this.title = title;
    this.content = content;
//    this.postImg = postImg;
  }
}
