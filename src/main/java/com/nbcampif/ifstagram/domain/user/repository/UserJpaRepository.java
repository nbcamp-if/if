package com.nbcampif.ifstagram.domain.user.repository;

import com.nbcampif.ifstagram.domain.user.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

}
