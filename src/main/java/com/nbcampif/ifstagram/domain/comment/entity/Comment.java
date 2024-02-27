package com.nbcampif.ifstagram.domain.comment.entity;



import com.nbcampif.ifstagram.domain.user.model.User;
import com.nbcampif.ifstagram.global.entity.Timestamped;
import com.nbcampif.ifstagram.domain.comment.dto.CommentRequestDto;
import com.nbcampif.ifstagram.global.entity.Timestamped;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "comments")
public class Comment extends Timestamped {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String content;

  @Column(nullable = false)
  private Long userId;

  @Column(nullable = false)
  private Long postId;

  @Column
  private Long parentCommentId;

  @Column
  private Boolean isDeleted = false;


    public Comment(CommentRequestDto requestDto, User user) {
        this.content = requestDto.getContent();
        this.userId = user.getUserId();
  }


  public void Delete() {
    this.isDeleted = true;
    super.deletedAt = LocalDateTime.now();
  }

}
