package com.nbcampif.ifstagram.domain.comment.service;

import com.nbcampif.ifstagram.domain.comment.dto.CommentRequestDto;
import com.nbcampif.ifstagram.domain.comment.dto.CommentResponseDto;
import com.nbcampif.ifstagram.domain.comment.entity.Comment;
import com.nbcampif.ifstagram.domain.comment.repository.CommentRepository;
import java.util.ArrayList;
import java.util.List;


import com.nbcampif.ifstagram.domain.post.entity.Post;
import com.nbcampif.ifstagram.domain.post.repository.PostRepository;
import com.nbcampif.ifstagram.domain.user.model.User;
import com.nbcampif.ifstagram.global.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository; // post repository 생성 후 수정
    public ResponseEntity<CommonResponse<List<CommentResponseDto>>> createComment(CommentRequestDto requestDto, Long postId, User user) {
        Post post = findPostById(postId); // validate post in repository
        Comment comment = new Comment(requestDto, user);
        comment.setPostId(post.getId());
        comment.setParentCommentId(0L); // root value
        commentRepository.save(comment);
        List<CommentResponseDto> response = getCommentAndReplyList(postId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<List<CommentResponseDto>>builder()
                        .message("댓글이 생성되었습니다.")
                        .data(response)
                        .build());
    }

    public ResponseEntity<CommonResponse<List<CommentResponseDto>>> createReplyComment(CommentRequestDto requestDto, Long postId, Long commentId, User user) {
        Post post = findPostById(postId);
        findCommentById(commentId);
        Comment comment = new Comment(requestDto, user);
        comment.setPostId(post.getId());
        comment.setParentCommentId(commentId);
        commentRepository.save(comment);
        List<CommentResponseDto> response = getCommentAndReplyList(postId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<List<CommentResponseDto>>builder()
                        .message("대댓글이 생성되었습니다.")
                        .data(response)
                        .build());
    }

    public ResponseEntity<CommonResponse<List<CommentResponseDto>>> getComment(Long postId) {
        List<CommentResponseDto> response = getCommentAndReplyList(postId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<List<CommentResponseDto>>builder()
                        .message("댓글이 조회되었습니다.")
                        .data(response)
                        .build());
    }

    public ResponseEntity<CommonResponse<List<CommentResponseDto>>> updateComment(CommentRequestDto requestDto, Long commentId, Long postId, User user) {
        findPostById(postId);
    Comment comment = findCommentById(commentId);
    comment.setContent(requestDto.getContent());
    commentRepository.save(comment);
    List<CommentResponseDto> response = getCommentAndReplyList(postId);
    return ResponseEntity.status(HttpStatus.OK)
        .body(CommonResponse.<List<CommentResponseDto>>builder()
            .message("댓글이 수정되었습니다.")
            .data(response)
            .build());
  }

  public ResponseEntity<CommonResponse<Void>> deleteComment(Long commentId) {
    Comment comment = findCommentById(commentId);
    comment.Delete();
    commentRepository.save(comment);
    return ResponseEntity.status(HttpStatus.OK)
        .body(CommonResponse.<Void>builder()
            .message("댓글이 삭제되었습니다.")
            .build());
  }

  private Comment findCommentById(Long commentId) {
    return commentRepository.findByIdAndIsDeleted(commentId, false)
        .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다."));

  }

    //Post entity 생성 후 재수정
    private Post findPostById(Long postId){
        return postRepository.findById(postId).orElseThrow(()-> new IllegalArgumentException("해당 포스트가 없습니다."));
    }

  private List<Comment> findByPostIdAndIsDeleted(Long postId, Boolean isdeleted) {
    return commentRepository.findByPostIdAndIsDeletedAndParentCommentId(postId, isdeleted, 0L)
        .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다."));
  }


  private List<CommentResponseDto> getCommentAndReplyList(Long postId) {
    List<CommentResponseDto> commentResponseList = new ArrayList<>();
    List<Comment> commentList = findByPostIdAndIsDeleted(postId, false);

    for (Comment c : commentList) {
      CommentResponseDto response = new CommentResponseDto(c);
      List<CommentResponseDto> replyList = commentRepository.findAllCommentByPostId(postId, c.getId())
          .stream().map(CommentResponseDto::new).toList();
      response.setReplyList(replyList);
      commentResponseList.add(response);
    }
    return commentResponseList;
  }

}
