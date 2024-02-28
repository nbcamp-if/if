package com.nbcampif.ifstagram.domain.user.repository.entity;

import com.nbcampif.ifstagram.domain.user.UserRole;
import com.nbcampif.ifstagram.domain.user.dto.UserUpdateRequestDto;
import com.nbcampif.ifstagram.domain.user.model.User;
import com.nbcampif.ifstagram.global.entity.Timestamped;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Optional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
@SQLRestriction(value = "deleted_at is NULL")
@SQLDelete(sql = "UPDATE users SET deleted_at = NOW() WHERE user_id = ?")
public class UserEntity extends Timestamped {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  @Column(unique = true)
  private String email;

  @Column(nullable = false)
  private String nickname;

  @Column(nullable = false)
  private String password;

  @Column
  private String profileImage;

  @Column
  private String introduction;

  @Column
  private Long reportedCount = 0L;

  @Column
  @Enumerated(value = EnumType.STRING)
  private UserRole role;

  public static UserEntity fromModel(User user) {
    return new UserEntity(
        user.getEmail(),
        user.getNickname(),
        user.getPassword(),
        user.getProfileImage(),
        user.getIntroduction(),
        user.getReportedCount(),
        user.getRole()
    );
  }

  private UserEntity(
      String email,
      String nickname,
      String password,
      String profileImage,
      String introduction,
      Long reportedCount,
      UserRole role
  ) {
    this.email = email;
    this.nickname = nickname;
    this.password = password;
    this.profileImage = profileImage;
    this.introduction = introduction;
    this.reportedCount = reportedCount;
    this.role = role;
  }

  public User toModel() {
    return new User(
        this.userId,
        this.email,
        this.nickname,
        this.password,
        this.profileImage,
        this.role
    );
  }

  public void update(UserUpdateRequestDto requestDto) {
    Optional.ofNullable(requestDto.getNickname())
        .ifPresent(requestNickname -> this.nickname = requestNickname);
    Optional.ofNullable(requestDto.getProfileImage())
        .ifPresent(requestProfileImage -> this.profileImage = requestProfileImage);
    Optional.ofNullable(requestDto.getIntroduction())
        .ifPresent(requestIntroduction -> this.introduction = requestIntroduction);
  }

  public void updateReportedCount() {
    this.reportedCount += 1;
  }

}
