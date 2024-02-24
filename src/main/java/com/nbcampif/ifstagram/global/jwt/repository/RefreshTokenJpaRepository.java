package com.nbcampif.ifstagram.global.jwt.repository;

import com.nbcampif.ifstagram.global.jwt.RefreshTokenEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RefreshTokenJpaRepository extends JpaRepository<RefreshTokenEntity, Long> {

  Optional<RefreshTokenEntity> findByToken(String refreshToken);

  void deleteByUserId(Long userId);

  void deleteByToken(String refreshToken);

}
