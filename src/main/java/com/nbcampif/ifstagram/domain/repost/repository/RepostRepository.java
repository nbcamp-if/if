package com.nbcampif.ifstagram.domain.repost.repository;

import com.nbcampif.ifstagram.domain.repost.entity.Repost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepostRepository extends JpaRepository<Repost, Long> {

}
