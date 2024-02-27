package com.nbcampif.ifstagram.global.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CommonResponse<T> {

  private Integer statusCode;
  private String msg;
  private T data;

  public static <T> ResponseEntity<CommonResponse<T>> ok(String msg, T data) {
    return ResponseEntity.ok()
        .body(CommonResponse.<T>builder()
            .statusCode(HttpStatus.OK.value())
            .msg(msg)
            .data(data)
            .build());
  }

}
