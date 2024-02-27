package com.nbcampif.ifstagram.domain.user.dto;

import com.nbcampif.ifstagram.domain.user.model.User;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private Long user_id;
    private String email;
    private String nickname;
    private String profile_img;
    private String introduction;

    public UserResponseDto(User user) {
        this.user_id = user.getUserId();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.profile_img = user.getProfileImage();
        this.introduction = user.getIntroduction();
    }
}
