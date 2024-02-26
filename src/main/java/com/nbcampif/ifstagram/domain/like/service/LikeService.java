package com.nbcampif.ifstagram.domain.like.service;

import com.nbcampif.ifstagram.domain.like.entity.Like;
import com.nbcampif.ifstagram.domain.like.repository.LikeRepository;
import com.nbcampif.ifstagram.domain.post.entity.Post;
import com.nbcampif.ifstagram.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {

  private final PostRepository postRepository;
  private final LikeRepository likeRepository;

  @Transactional
  public Long countLike(Long postId) {
    // postID로 postRepository조회해서 해당 게시글 정보 가져오기
    Post postSeq = postRepository.findById(postId).orElseThrow(()
        -> new IllegalCallerException("일치하는 게시글이 없습니다."));

    // 사용자 확인: 사용자 아이디로 조회해서 해당 postId글에 좋아요했는지 조회

    // 이 사용자가 해당 postId에 좋아요를 하지 않았다면 아래가 수행됨

    // likeRepository에 postId, userId를 저장
    Long userId = 1L;
    Like like = new Like(userId, postId);
    likeRepository.save(like);

    // count 수를 올려서 수정
    postRepository.updateCount(postId);

    Long count = postRepository.findById(postId).get().getLikeCount()+1;

    return count;
  }
}
