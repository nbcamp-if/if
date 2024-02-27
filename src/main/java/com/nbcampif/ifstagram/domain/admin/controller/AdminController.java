package com.nbcampif.ifstagram.domain.admin.controller;

import com.nbcampif.ifstagram.domain.admin.dto.LoginRequestDto;
import com.nbcampif.ifstagram.domain.admin.service.AdminService;
import com.nbcampif.ifstagram.global.response.CommonResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Admin Controller", description = "관리자 컨트롤러")
@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<CommonResponse<Void>> adminLogin(
        @RequestBody LoginRequestDto requestDto,
        HttpServletResponse response
    ) {
        adminService.login(requestDto, response);
        return ResponseEntity.status(HttpStatus.OK.value()).body(
            CommonResponse.<Void>builder().message("로그인 성공").build()
        );
    }
}
