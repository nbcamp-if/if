package com.nbcampif.ifstagram.domain.image.repository;

import com.nbcampif.ifstagram.domain.image.entity.PostImage;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {

  Optional<PostImage> findByPostId(Long postId);
}
