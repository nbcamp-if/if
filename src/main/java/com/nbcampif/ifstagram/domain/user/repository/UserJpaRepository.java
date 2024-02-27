package com.nbcampif.ifstagram.domain.user.repository;

import com.nbcampif.ifstagram.domain.user.UserRole;
import com.nbcampif.ifstagram.domain.user.model.User;
import com.nbcampif.ifstagram.domain.user.repository.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByRole(UserRole userRole);

<<<<<<< HEAD
    User findByEmail(String email);
=======
    Optional<UserEntity> findByEmail(String email);
>>>>>>> e631d145fc0deebfb8f965fda656f46bc6548ab1

    Optional<UserEntity> findById(Long id);
}
