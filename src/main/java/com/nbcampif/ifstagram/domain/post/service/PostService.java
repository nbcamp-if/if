package com.nbcampif.ifstagram.domain.post.service;

import com.nbcampif.ifstagram.domain.image.service.PostImageService;
import com.nbcampif.ifstagram.domain.post.dto.PostRequestDto;
import com.nbcampif.ifstagram.domain.post.dto.PostResponseDto;
import com.nbcampif.ifstagram.domain.post.entity.Post;
import com.nbcampif.ifstagram.domain.post.repository.PostRepository;
import com.nbcampif.ifstagram.domain.user.model.User;
import com.nbcampif.ifstagram.domain.user.repository.UserRepository;
import com.nbcampif.ifstagram.global.response.CommonResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;
  private final UserRepository userRepository;
  private final PostImageService postImageService;

  @Transactional
  public void createPost(
      PostRequestDto requestDto, MultipartFile image, User user) throws Exception {
    Post post = new Post(requestDto, user.getUserId());
    postRepository.save(post);

    postImageService.createImage(image, post);
  }

  @Transactional(readOnly = true)
  public List<PostResponseDto> getPostList() {
    return postRepository.findAll().stream().map(post -> {
        String imageUrl;
        try {
          imageUrl = postImageService.getImage(post.getId());
        } catch (MalformedURLException ex) {
          throw new RuntimeException(ex);
        }
        return new PostResponseDto(post, imageUrl);
      })
      .toList();
  }

  @Transactional(readOnly = true)
  public PostResponseDto getPost(Long postId) throws MalformedURLException {
    Post post = findPost(postId);
    PostResponseDto responseDto = new PostResponseDto(
      post,
      postImageService.getImage(post.getId())
    );
    return responseDto;
  }

  @Transactional
  public void updatePost(Long postId, PostRequestDto requestDto, MultipartFile image)
    throws IOException {

    Post post = findPost(postId);
    postImageService.updateImage(post, image);

    post.updatePost(requestDto);
  }

  @Transactional
  public void deletePost(Long postId) {
    Post post = findPost(postId);

    postRepository.delete(post);
  }

  private Post findPost(Long postId) {
    return postRepository.findById(postId).orElseThrow(()
        -> new IllegalCallerException("일치하는 게시글이 없습니다."));

    // todo: 사진을 삭제해도 url이 남아 있고 db를 삭제해도 사진이 남아 있음
  }
}
