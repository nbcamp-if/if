package com.nbcampif.ifstagram.domain.comment.service;

import com.nbcampif.ifstagram.domain.comment.dto.CommentRequestDto;
import com.nbcampif.ifstagram.domain.comment.dto.CommentResponseDto;
import com.nbcampif.ifstagram.domain.comment.entity.Comment;
import com.nbcampif.ifstagram.domain.comment.repository.CommentRepository;
import java.util.ArrayList;
import java.util.List;

import com.nbcampif.ifstagram.global.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
//    private final PostRepository postRepository;
    public ResponseEntity<CommonResponse<List<CommentResponseDto>>> createComment(CommentRequestDto requestDto, Long postId) {
//        Post post = findPostById(postId);
        Comment comment = new Comment(requestDto);
        comment.setPostId(postId);
        comment.setUserId(1L);
        comment.setParentCommentId(0L);
        commentRepository.save(comment);
        List<CommentResponseDto> response = getCommentAndReplyList(postId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<List<CommentResponseDto>>builder()
                        .message("댓글이 생성되었습니다.")
                        .data(response)
                        .build());
    }


    public ResponseEntity<CommonResponse<List<CommentResponseDto>>> createReplyComment(CommentRequestDto requestDto, Long postId, Long commentId) {
//        Post post = findPostById(postId);
        findCommentById(commentId);
        Comment comment = new Comment(requestDto);
        comment.setPostId(postId);
        comment.setParentCommentId(commentId);
        comment.setUserId(1L);
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

    public ResponseEntity<CommonResponse<List<CommentResponseDto>>> updateComment(CommentRequestDto requestDto, Long commentId, Long postId) {
//        findPostById(postId);
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

//    //Post entity 생성 후 재수정
//    private Post findPostById(Long postId){
//        return postRepository.findById(postId).orElseThrow(()-> new IllegalArgumentException("해당 포스트가 없습니다."));
//    }

    private List<Comment> findByPostIdAndIsDeleted(Long postId, Boolean isdeleted){
        return commentRepository.findByPostIdAndIsDeletedAndParentCommentId(postId, isdeleted, 0L)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다."));
    }


    private List<CommentResponseDto> getCommentAndReplyList(Long postId) {
        List<CommentResponseDto> commentResponseList = new ArrayList<>();
        List<Comment> commentList = findByPostIdAndIsDeleted(postId, false);

        for(Comment c : commentList){
            CommentResponseDto response = new CommentResponseDto(c);
            List<CommentResponseDto> replyList = commentRepository.findAllCommentByPostId(postId, c.getId())
                    .stream().map(CommentResponseDto::new).toList();
            response.setReplyList(replyList);
            commentResponseList.add(response);
        }
        return commentResponseList;
    }


}
