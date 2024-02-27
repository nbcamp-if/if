package com.nbcampif.ifstagram.domain.comment.dto;


import com.nbcampif.ifstagram.domain.comment.entity.Comment;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentResponseDto {
    private Long postId;
    private Long commentId;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;
    private LocalDateTime deletedDate;
    private Long username;
    private String content;
    private List<CommentResponseDto> replyList = new ArrayList<>();

    public CommentResponseDto(Comment comment){
        this.postId = comment.getPostId();
        this.commentId = comment.getId();
        this.username = comment.getUserId();
        this.content = comment.getContent();
        this.createDate = comment.getCreatedAt();
        this.modifiedDate = comment.getModifiedAt();
        this.deletedDate = comment.getDeletedAt();

}
