package com.nbcampif.ifstagram.domain.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Follow {

  private Long fromUserId;
  private Long toUserId;

}
