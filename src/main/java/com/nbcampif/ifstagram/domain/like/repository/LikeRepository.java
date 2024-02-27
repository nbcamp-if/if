package com.nbcampif.ifstagram.domain.like.repository;

import com.nbcampif.ifstagram.domain.like.entity.Like;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {

  Like findByUserIdAndPostId(Long userId, Long postId);



}
