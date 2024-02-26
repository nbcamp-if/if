package com.nbcampif.ifstagram.domain.repost.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "reposts")
@NoArgsConstructor
public class Repost {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long repostId;

  @Column
  private Long postId;

  @Column
  private Long userId;

}
