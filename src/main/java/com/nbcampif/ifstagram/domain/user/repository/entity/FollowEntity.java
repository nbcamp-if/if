package com.nbcampif.ifstagram.domain.user.repository.entity;

import com.nbcampif.ifstagram.domain.user.model.Follow;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "follows")
public class FollowEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long followId;

  @Column(nullable = false)
  private Long fromUserId;

  @Column(nullable = false)
  private Long toUserId;

  private FollowEntity(Long fromUserId, Long toUserId) {
    this.fromUserId = fromUserId;
    this.toUserId = toUserId;
  }

  public static FollowEntity of(Long fromUserId, Long toUserId) {
    return new FollowEntity(fromUserId, toUserId);
  }

  public static FollowEntity of(Follow follow) {
    return new FollowEntity(follow.getFromUserId(), follow.getToUserId());
  }

  public Follow toModel() {
    return new Follow(this.fromUserId, this.toUserId);
  }

}
