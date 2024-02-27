package com.nbcampif.ifstagram.domain.user.repository;

import com.nbcampif.ifstagram.domain.user.repository.entity.FollowEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowJpaRepository extends JpaRepository<FollowEntity, Long> {

  Optional<FollowEntity> findByFromUserIdAndToUserId(Long fromUserId, Long toUserId);

}
