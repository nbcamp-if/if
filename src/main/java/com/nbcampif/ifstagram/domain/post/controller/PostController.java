package com.nbcampif.ifstagram.domain.post.controller;

import com.nbcampif.ifstagram.domain.post.dto.PostRequestDto;
import com.nbcampif.ifstagram.domain.post.entity.Post;
import com.nbcampif.ifstagram.domain.post.service.PostService;
import com.nbcampif.ifstagram.domain.user.model.User;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
  public String createPost(
      @RequestPart(value = "data") PostRequestDto requestDto,
      @RequestPart(value = "file") MultipartFile image,
      @AuthenticationPrincipal User user) throws IOException {

    postService.createPost(requestDto, image, user);

    return "게시글이 등록되었습니다.";
  }

  @GetMapping
  public ResponseEntity<List<Post>> getPostList() {
    return new ResponseEntity<>(postService.getPostList(), HttpStatus.OK);
  }

  @GetMapping("/{postId}")
  public ResponseEntity<Post> getPost(
      @PathVariable Long postId) {
    return new ResponseEntity<>(postService.getPost(postId), HttpStatus.OK);
  }

  @PutMapping("/{postId}")
  public String updatePost(
      @PathVariable Long postId,
      @RequestBody PostRequestDto requestDto) {
    postService.updatePost(postId, requestDto);
    return "게시글이 수정되었습니다.";
  }

  @DeleteMapping("/{postId}")
  public String deletePost(
      @PathVariable Long postId) {
    postService.deletePost(postId);

    return "게시글이 삭제되었습니다.";
  }
}
