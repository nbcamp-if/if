package com.nbcampif.ifstagram.domain.comment.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Getter
public class CommentRequestDto {
    private String content;
}
