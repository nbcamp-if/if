package com.nbcampif.ifstagram.global.jwt;

import org.springframework.data.jpa.repository.JpaRepository;


public interface RefreshTokenJpaRepository extends JpaRepository<RefreshTokenEntity, Long> {

}
