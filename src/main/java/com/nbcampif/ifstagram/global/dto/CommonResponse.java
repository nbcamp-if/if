package com.nbcampif.ifstagram.global.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CommonResponse<T> {

    private Integer statusCode;
    private String msg;
    private T data;

}
