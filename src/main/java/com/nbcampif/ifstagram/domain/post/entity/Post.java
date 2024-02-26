package com.nbcampif.ifstagram.domain.post.entity;

import com.nbcampif.ifstagram.domain.post.dto.PostRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "posts")
@NoArgsConstructor
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long postId;
  @Column(nullable = false)
  private String title;
  @Lob
  @Column(nullable = false)
  private String content;
  @Column(nullable = true)
  private String postImg;
  @Column(nullable = false, columnDefinition = "BIGINT default 0")
  private Long likeCount;
//  @Column(nullable = false)
  private Long repostCount;
//  @Column(nullable = false)
  private Long userId;
//  @Column(nullable = false)
  private Long repostId;
  @Column(nullable = false)
  private LocalDateTime createdAt;
  @Column(nullable = false)
  private LocalDateTime modifiedAt;
  @Column
  private LocalDateTime deletedAt;

  public Post(PostRequestDto requestDto, String image) {
    this.title = requestDto.getTitle();
    this.content = requestDto.getContent();
    this.postImg = image;
    this.likeCount = 0L;
    this.createdAt = LocalDateTime.now();
    this.modifiedAt = LocalDateTime.now();
  }

  public void updatePost(PostRequestDto requestDto) {
    this.title = requestDto.getTitle();
    this.content = requestDto.getContent();
    this.modifiedAt = LocalDateTime.now();
  }

  public void updateLike() {
    this.likeCount++;
  }

//  public Post(PostRequestDto requestDto, userDetails userDetails) {
//    this.title = requestDto.getTitle();
//    this.content = requestDto.getContent();
//    this.postImg = requestDto.getPostImg();
//  }
}
