package com.nbcampif.ifstagram.domain.report.controller;


import com.nbcampif.ifstagram.domain.report.dto.ReportRequestDto;
import com.nbcampif.ifstagram.domain.report.service.ReportService;
import com.nbcampif.ifstagram.domain.user.model.User;
import com.nbcampif.ifstagram.global.response.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/reports/user")
@RestController
public class ReportController {
    private final ReportService reportService;
    //신고기능

    @Operation(summary = "회원 신고", description = "회원 신고")
    @PostMapping("/{userId}")
    public ResponseEntity<CommonResponse<?>> reportUser(
            @PathVariable Long userId,
            @AuthenticationPrincipal User user,
            @RequestBody ReportRequestDto requestDto) {
        System.out.println(userId);
        return reportService.reportUser(userId, user, requestDto);
    }
}
