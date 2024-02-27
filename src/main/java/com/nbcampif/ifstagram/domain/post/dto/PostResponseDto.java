package com.nbcampif.ifstagram.domain.post.dto;

import com.nbcampif.ifstagram.domain.post.entity.Post;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponseDto {

  private final String title;
  private final String content;
  private final String postImg;
  private List<PostResponseDto> postList = new ArrayList<>();

  public PostResponseDto(String title, String content, String postImg) {
    this.title = title;
    this.content = content;
    this.postImg = postImg;
  }

  public PostResponseDto(Post post) {
    this.title = post.getTitle();
    this.content = post.getContent();
    this.postImg = post.getPostImg();
  }
}
