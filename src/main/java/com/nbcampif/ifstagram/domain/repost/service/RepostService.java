package com.nbcampif.ifstagram.domain.repost.service;

import com.nbcampif.ifstagram.domain.post.entity.Post;
import com.nbcampif.ifstagram.domain.post.repository.PostRepository;
import com.nbcampif.ifstagram.domain.repost.dto.RepostResponseDto;
import com.nbcampif.ifstagram.domain.repost.repository.RepostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RepostService {

  private final RepostRepository repostRepository;
  private final PostRepository postRepository;

  public RepostResponseDto updateRepost(Long postId) {
    Post post = postRepository.findById(postId).orElseThrow(()
      -> new IllegalCallerException("일치하는 게시글이 없습니다."));

    RepostResponseDto responseDto = new RepostResponseDto(
        post.getTitle(),
        post.getContent(),
        post.getPostImg()
    );

    return responseDto;
  }
}
