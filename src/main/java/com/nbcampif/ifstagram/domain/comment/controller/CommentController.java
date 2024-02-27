package com.nbcampif.ifstagram.domain.comment.controller;


import com.nbcampif.ifstagram.domain.comment.dto.CommentRequestDto;
import com.nbcampif.ifstagram.domain.comment.dto.CommentResponseDto;
import com.nbcampif.ifstagram.domain.comment.service.CommentService;
import com.nbcampif.ifstagram.global.response.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments/post")
public class CommentController {

  private final CommentService commentService;

  @Operation(summary = "댓글 생성", description = "댓글 생성")
  @PostMapping("/{postId}")
  public ResponseEntity<CommonResponse<List<CommentResponseDto>>> createComment(
      @PathVariable Long postId,
      @RequestBody CommentRequestDto requestDto
  ) {
    return commentService.createComment(requestDto, postId);
  }

  @Operation(summary = "대댓글 생성", description = "대댓글 생성")
  @PostMapping("/{postId}/reply/{commentId}")
  public ResponseEntity<CommonResponse<List<CommentResponseDto>>> createReplyComment(
      @PathVariable Long postId,
      @PathVariable Long commentId,
      @RequestBody CommentRequestDto requestDto
  ) {
    return commentService.createReplyComment(requestDto, postId, commentId);
  }

  @Operation(summary = "댓글 조회", description = "댓글 조회")
  @GetMapping("/{postId}")
  public ResponseEntity<CommonResponse<List<CommentResponseDto>>> getComment(
      @PathVariable Long postId,
      @RequestBody CommentRequestDto requestDto
  ) {
    return commentService.getComment(postId);
  }

  @Operation(summary = "댓글 수정", description = "댓글 수정")
  @PutMapping("/{postId}/{commentId}")
  public ResponseEntity<CommonResponse<List<CommentResponseDto>>> updateComment(
      @PathVariable Long commentId,
      @PathVariable Long postId,
      @RequestBody CommentRequestDto requestDto
  ) {
    return commentService.updateComment(requestDto, commentId, postId);
  }

  @Operation(summary = "댓글 삭제", description = "댓글 삭제")
  @DeleteMapping("{commentId}")
  public ResponseEntity<CommonResponse<Void>> deleteComment(
      @PathVariable Long commentId
  ) {
    return commentService.deleteComment(commentId);
  }

}
