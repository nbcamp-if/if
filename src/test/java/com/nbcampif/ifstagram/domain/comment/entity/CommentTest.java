package com.nbcampif.ifstagram.domain.comment.entity;

import com.nbcampif.ifstagram.domain.comment.dto.CommentRequestDto;
import com.nbcampif.ifstagram.domain.post.entity.Post;
import com.nbcampif.ifstagram.domain.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;


class CommentTest {
    private CommentRequestDto requestDto;
    @Mock
    private User user;
    @Mock
    private Post post;

    @BeforeEach
    public void setup() {
        user = new User(1L, "test nickname", "test email", "test image");
        post = new Post();
        post.setPostId(1L);

        requestDto = new CommentRequestDto();
        String content = "test comment";
        requestDto.setContent(content);
    }

    @Test
    public void testCommentEntityConstructor() {
        Comment comment = new Comment(requestDto, user);

        assertEquals(requestDto.getContent(), comment.getContent());
        assertEquals(user.getUserId(), comment.getUserId());
        assertFalse(comment.getIsDeleted());
    }

    @Test
    public void testCommentDeleteMethod() {
        Comment comment = new Comment(requestDto, user);
        assertFalse(comment.getIsDeleted());

        comment.Delete();
        assertTrue(comment.getIsDeleted());
    }


}