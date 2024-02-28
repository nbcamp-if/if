package com.nbcampif.ifstagram.domain.user.repository;

import com.nbcampif.ifstagram.domain.user.repository.entity.RecentPassword;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecentPasswordRepository extends JpaRepository<RecentPassword, Long> {

  List<RecentPassword> findTop3RecentPasswordsByUserIdOrderByCreatedAtDesc(Long userId);
}
