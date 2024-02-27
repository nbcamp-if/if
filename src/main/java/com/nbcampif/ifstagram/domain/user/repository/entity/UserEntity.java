package com.nbcampif.ifstagram.domain.user.repository.entity;

import com.nbcampif.ifstagram.global.entity.Timestamped;
import com.nbcampif.ifstagram.domain.user.UserRole;
import com.nbcampif.ifstagram.domain.user.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@SQLRestriction(value = "deleted_at is NULL")
@SQLDelete(sql = "UPDATE users SET deleted_at = NOW() WHERE user_id = ?")
public class UserEntity extends Timestamped {

  @Id
  private Long userId;

  @Column(unique = true)

  private String email;

  @Column(nullable = false)
  private String nickname;

  @Column
  private String profileImage;

  @Column
  private String introduction;

  @Column
  private Long reportedCount;

  @Column
  @Enumerated(value = EnumType.STRING)
  private UserRole role;

  public static UserEntity fromModel(User user) {
    return new UserEntity(
        user.getUserId(),
        user.getEmail(),
        user.getNickname(),
        user.getProfileImage(),
        user.getIntroduction(),
        user.getReportedCount(),
        user.getRole()
    );
  }

  public User toModel() {
    return new User(userId, email, nickname, profileImage, introduction, reportedCount, role);
  }

  public void updateReportedCount(){
    this.reportedCount +=1;
  }


}
