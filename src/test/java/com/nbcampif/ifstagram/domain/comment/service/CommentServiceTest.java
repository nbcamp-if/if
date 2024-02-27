package com.nbcampif.ifstagram.domain.comment.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import com.nbcampif.ifstagram.domain.comment.dto.CommentRequestDto;
import com.nbcampif.ifstagram.domain.comment.dto.CommentResponseDto;
import com.nbcampif.ifstagram.domain.comment.entity.Comment;
import com.nbcampif.ifstagram.domain.comment.repository.CommentRepository;
import com.nbcampif.ifstagram.domain.post.dto.PostRequestDto;
import com.nbcampif.ifstagram.domain.post.dto.PostResponseDto;
import com.nbcampif.ifstagram.domain.post.entity.Post;
import com.nbcampif.ifstagram.domain.post.repository.PostRepository;
import com.nbcampif.ifstagram.domain.user.model.User;
import com.nbcampif.ifstagram.global.response.CommonResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private CommentService commentService;

    private User user;
    private CommentRequestDto requestDto;

    @BeforeEach
    void setUp() {
        // 초기화 코드 작성
        user = new User(1L, "test nickname", "test email", "test image");
        requestDto = new CommentRequestDto();
        String content = "test comment";
        requestDto.setContent(content);

        CommentRequestDto commentRequestDto = new CommentRequestDto();
        commentRequestDto.setContent("test comment");
        Comment testcomment = new Comment(commentRequestDto, user);
        testcomment.setParentCommentId(0L);
        testcomment.setPostId(1L);
        testcomment.setIsDeleted(false);
    }

    @Test
    void createComment_ReturnSuccessResponse() {
        // given
        PostRequestDto postRequestDto = new PostRequestDto();
        postRequestDto.setContent("test");
        Post post = new Post(postRequestDto,"test image", user.getUserId());
        Long postId = 1L;
        post.setPostId(postId);

        // when
        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        ResponseEntity<CommonResponse<List<CommentResponseDto>>> response = commentService.createComment(requestDto, postId, user);

        // then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getMessage()).isEqualTo("댓글이 생성되었습니다.");
    }
}
