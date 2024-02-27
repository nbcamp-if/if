package com.nbcampif.ifstagram.domain.comment.repository;

import com.nbcampif.ifstagram.domain.comment.dto.CommentRequestDto;
import com.nbcampif.ifstagram.domain.comment.entity.Comment;
import com.nbcampif.ifstagram.domain.post.dto.PostRequestDto;
import com.nbcampif.ifstagram.domain.post.entity.Post;
import com.nbcampif.ifstagram.domain.user.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CommentRepository commentRepository;

    private User user;
    private Post post;

    public void setup() {
        user = new User(1L,"test1234", "pwpw1234", "test image");
        PostRequestDto requestDto = new PostRequestDto();
        requestDto.setContent("test contents");
        requestDto.setTitle("test title");
        post = new Post(requestDto, "test image", user.getUserId());
    }

    @Test
    public void testSaveComment() {
        this.setup();
        // given
        CommentRequestDto requestDto = new CommentRequestDto();
        requestDto.setContent("test comment");
        Comment comment = new Comment(requestDto, user);
        comment.setUserId(user.getUserId());

        // when
        Comment savedComment = commentRepository.save(comment);

        // then
        assertTrue(savedComment.getId() > 0);
        assertEquals("test comment", savedComment.getContent());
    }
}
