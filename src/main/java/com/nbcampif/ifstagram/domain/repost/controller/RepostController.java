package com.nbcampif.ifstagram.domain.repost.controller;

import com.nbcampif.ifstagram.domain.repost.dto.RepostResponseDto;
import com.nbcampif.ifstagram.domain.repost.service.RepostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts/{postId}/repost")
public class RepostController {

  private final RepostService repostService;

  @PostMapping
  public RepostResponseDto updateRepost (
      @PathVariable Long postId) {

    RepostResponseDto responseDto = repostService.updateRepost(postId);

    return responseDto;
  }

}
