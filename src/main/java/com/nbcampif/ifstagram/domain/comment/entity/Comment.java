package com.nbcampif.ifstagram.domain.comment.entity;


import com.nbcampif.ifstagram.domain.user.model.User;
import com.nbcampif.ifstagram.global.entity.Timestamped;
import com.nbcampif.ifstagram.domain.comment.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "comment")
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
