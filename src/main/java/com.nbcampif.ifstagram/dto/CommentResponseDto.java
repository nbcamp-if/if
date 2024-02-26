package com.nbcampif.ifstagram.dto;


import com.nbcampif.ifstagram.entity.Comment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class CommentResponseDto {
    private Long postId;
    private Long commentId;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;
    private String deletedDate;
    private String username;
    private String content;
    private List<CommentResponseDto> replyList = new ArrayList<>();

    public CommentResponseDto(Comment comment){
        this.postId = comment.getPostId();
        this.commentId = comment.getId();
        this.username = "test";
        this.content = comment.getContent();
        this.createDate = comment.getCreatedAt();
        this.modifiedDate = comment.getModifiedAt();
        this.deletedDate = comment.getDeletedAt();

    }
}
