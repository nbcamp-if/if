package com.nbcampif.ifstagram.domain.post.controller;

import com.nbcampif.ifstagram.domain.post.dto.PostRequestDto;
import com.nbcampif.ifstagram.domain.post.dto.PostResponseDto;
import com.nbcampif.ifstagram.domain.post.entity.Post;
import com.nbcampif.ifstagram.domain.post.service.PostService;
import com.nbcampif.ifstagram.domain.user.model.User;
import com.nbcampif.ifstagram.global.response.CommonResponse;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {

  private final PostService postService;

  @PostMapping
  public ResponseEntity<CommonResponse<Void>> createPost(
      @RequestPart(value = "data") PostRequestDto requestDto,
      @RequestPart(value = "file") MultipartFile image,
      @AuthenticationPrincipal User user) throws IOException {

    postService.createPost(requestDto, image, user);

    return ResponseEntity.status(HttpStatus.OK.value()).body(
        CommonResponse.<Void>builder().message("게시글 생성").build());
  }

  @GetMapping
  public ResponseEntity<CommonResponse<List<PostResponseDto>>> getPostList() {
    List<PostResponseDto> responseDto = postService.getPostList();
    return ResponseEntity.status(HttpStatus.OK.value()).body(
        CommonResponse.<List<PostResponseDto>>builder()
            .message("게시글 전체 조회 성공")
            .data(responseDto)
            .build());
  }

  @GetMapping("/{postId}")
  public ResponseEntity<CommonResponse<PostResponseDto>> getPost(
      @PathVariable Long postId) {
    PostResponseDto responseDto = postService.getPost(postId);
    return ResponseEntity.status(HttpStatus.OK.value()).body(
        CommonResponse.<PostResponseDto>builder()
            .message("게시글 조회 성공")
            .data(responseDto)
            .build());
  }

  @PutMapping("/{postId}")
  public ResponseEntity<CommonResponse<Void>> updatePost(
      @PathVariable Long postId,
      @RequestBody PostRequestDto requestDto) {
    postService.updatePost(postId, requestDto);
    return ResponseEntity.status(HttpStatus.OK.value()).body(
        CommonResponse.<Void>builder().message("게시글 수정 성공").build());
  }

  @DeleteMapping("/{postId}")
  public ResponseEntity<CommonResponse<Void>> deletePost(
      @PathVariable Long postId) {
    postService.deletePost(postId);

    return ResponseEntity.status(HttpStatus.OK.value()).body(
        CommonResponse.<Void>builder().message("게시글 삭제 성공").build());
  }
}
