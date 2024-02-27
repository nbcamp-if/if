package com.nbcampif.ifstagram.domain.repost.controller;

import com.nbcampif.ifstagram.domain.repost.service.RepostService;
import com.nbcampif.ifstagram.domain.user.model.User;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts/{postId}/repost")
public class RepostController {

  private final RepostService repostService;

  @PostMapping
  public String createRepost(
      @PathVariable Long postId,
      @AuthenticationPrincipal User user) throws IOException {

    repostService.createRepost(postId, user);

    return "리포스팅 게시글이 생성되었습니다.";
  }
}
