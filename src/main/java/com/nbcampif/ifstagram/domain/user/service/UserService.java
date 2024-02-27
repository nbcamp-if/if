package com.nbcampif.ifstagram.domain.user.service;

import com.nbcampif.ifstagram.domain.user.model.User;
import com.nbcampif.ifstagram.domain.user.repository.UserRepository;
import com.nbcampif.ifstagram.domain.user.repository.entity.UserEntity;
import com.nbcampif.ifstagram.global.dto.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public ResponseEntity<CommonResponse<?>> reportUser(Long userId) {
        User reportedUser = findUserById(userId);
        System.out.println(userId);
        userRepository.updateReportedCount(reportedUser);

        return ResponseEntity.ok()
                .body(new CommonResponse<>(HttpStatus.OK.value(), "유저가 신고되었습니다", null));
    }

    public User findUserById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
    }
}
