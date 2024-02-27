package com.nbcampif.ifstagram.domain.repost.controller;

import com.nbcampif.ifstagram.domain.repost.dto.RepostRequestDto;
import com.nbcampif.ifstagram.domain.repost.dto.RepostResponseDto;
import com.nbcampif.ifstagram.domain.repost.service.RepostService;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts/{postId}/repost")
public class RepostController {

  private final RepostService repostService;

  @PostMapping
  public RepostResponseDto createRepost(
      @PathVariable Long postId,
      @AuthenticationPrincipal User user) throws IOException {

    RepostResponseDto responseDto = repostService.createRepost(postId, user);

    return responseDto;
  }

}
