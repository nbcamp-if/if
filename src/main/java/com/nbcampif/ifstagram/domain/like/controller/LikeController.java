package com.nbcampif.ifstagram.domain.like.controller;

import com.nbcampif.ifstagram.domain.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class LikeController {

  private final LikeService likeService;

  @PostMapping("/{postId}/likes")
  public String countLike (
      @PathVariable Long postId) {
    Long count = likeService.countLike(postId);

    return "좋아요 " + count + "개";
  }
}
