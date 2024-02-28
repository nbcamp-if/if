package com.nbcampif.ifstagram.domain.user.dto;

import lombok.Getter;

@Getter
public class ReportReponseDto {

    private String content;
    private Long from_user_id;
    private Long to_user_id;


    public ReportReponseDto(String content, Long from_user_id, Long to_user_id) {
        this.content = content;
        this.from_user_id = from_user_id;
        this.to_user_id = to_user_id;
    }
}
