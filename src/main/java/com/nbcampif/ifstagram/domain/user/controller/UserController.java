package com.nbcampif.ifstagram.domain.user.controller;

import com.nbcampif.ifstagram.global.dto.CommonResponse;
import com.nbcampif.ifstagram.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RequestMapping("/api/v1/reports/users")
@RestController
public class UserController {
    private final UserService userService;
    //신고기능

    @Operation(summary = "회원 신고", description = "회원 신고")
    @PostMapping("/{userId}")
    public ResponseEntity<CommonResponse<?>> reportUser(
            @PathVariable Long userId) {
        System.out.println(userId);
        return userService.reportUser(userId);
    }
}
