package com.nbcampif.ifstagram.global.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TokenRepository {

  private final RefreshTokenJpaRepository refreshTokenJpaRepository;

  public void saveToken(Long memberId, String token) {
    RefreshTokenEntity entity = RefreshTokenEntity.of(memberId, token);
    refreshTokenJpaRepository.save(entity);
  }

  public void deleteByUserId(Long userId) {
    refreshTokenJpaRepository.deleteByUserId(userId);
  }

}
