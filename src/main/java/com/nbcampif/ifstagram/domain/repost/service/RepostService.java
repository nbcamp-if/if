package com.nbcampif.ifstagram.domain.repost.service;

import com.nbcampif.ifstagram.domain.post.entity.Post;
import com.nbcampif.ifstagram.domain.post.repository.PostRepository;
import com.nbcampif.ifstagram.domain.repost.dto.RepostRequestDto;
import com.nbcampif.ifstagram.domain.repost.dto.RepostResponseDto;
import com.nbcampif.ifstagram.domain.repost.entity.Repost;
import com.nbcampif.ifstagram.domain.repost.repository.RepostRepository;
import com.nbcampif.ifstagram.domain.user.model.User;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class RepostService {

  private final RepostRepository repostRepository;
  private final PostRepository postRepository;

  @Transactional
  public RepostResponseDto createRepost(
      Long postId, User user) throws IOException {
    // 원본 게시글 정보 가져옴
    Post post = postRepository.findById(postId).orElseThrow(()
    -> new IllegalCallerException("일치하는 게시글이 없습니다."));

    // 원본 게시글 새롭게 저장



    // 원본 게시글을 새롭게 저장 repost id를 가져온 post id로 저장

    return null;
  }
}

/*
    String uuid = UUID.randomUUID().toString();
    Post post = postRepository.findById(postId).orElseThrow(()
        -> new IllegalCallerException("일치하는 게시글이 없습니다."));

    // 이미지 경로 및 저장
    String fileName = image.getOriginalFilename();
    Path path = Paths.get(
        System.getProperty("user.home"), "Desktop", "IFstagram");
    Path filePath = path.resolve(uuid + fileName);
    String postImage = filePath + "";

    if (!Files.exists(path)) {
      Files.createDirectories(path);
    }

    Files.copy(image.getInputStream(), filePath);
 */
