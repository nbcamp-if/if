package com.nbcampif.ifstagram.global.jwt.repository;

import com.nbcampif.ifstagram.global.jwt.RefreshTokenEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepository {

  private final RefreshTokenJpaRepository refreshTokenJpaRepository;

  public void saveToken(Long memberId, String refreshToken) {
    RefreshTokenEntity entity = RefreshTokenEntity.of(memberId, refreshToken);
    refreshTokenJpaRepository.save(entity);
  }

  public Long findUserIdByTokenOrElseThrow(String refreshToken) {
    return refreshTokenJpaRepository.findByToken(refreshToken)
        .map(RefreshTokenEntity::getUserId)
        .orElseThrow(() -> new EntityNotFoundException("RefreshTokenEntity not found"));
  }

  public void deleteTokenByUserId(Long userId) {
    refreshTokenJpaRepository.deleteByUserId(userId);
  }

  public void deleteToken(String refreshToken) {
    refreshTokenJpaRepository.deleteByToken(refreshToken);
  }

}
