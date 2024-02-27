package com.nbcampif.ifstagram.entity;


import com.nbcampif.ifstagram.dto.CommentRequestDto;
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
    private String deletedAt;

    @Column
    private Boolean isDeleted = false;

//    @Column
//    private User user;

    public Comment(CommentRequestDto requestDto) {
        this.content = requestDto.getContent();
        //temporary

    }

    public void Delete() {
        this.isDeleted = true;
        this.deletedAt = String.valueOf(LocalDateTime.now());
    }
}
