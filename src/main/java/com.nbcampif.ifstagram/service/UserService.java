package com.nbcampif.ifstagram.service;

import com.nbcampif.ifstagram.CommonResponse;
import com.nbcampif.ifstagram.entity.User;
import com.nbcampif.ifstagram.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public ResponseEntity<CommonResponse<?>> reportUser(Long userId) {
        User reportedUser = findUserById(userId);
        System.out.println(userId);
        reportedUser.setReportedCount(reportedUser.getReportedCount()+1);
        userRepository.save(reportedUser);

        return ResponseEntity.ok()
                .body(new CommonResponse<>(HttpStatus.OK.value(), "유저가 신고되었습니다", null));
    }

    public User findUserById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
    }
}
