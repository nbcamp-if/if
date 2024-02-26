package com.nbcampif.ifstagram.domain.admin.controller;

import com.nbcampif.ifstagram.domain.admin.service.AdminService;
import com.nbcampif.ifstagram.domain.user.model.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Admin Controller", description = "관리자 컨트롤러")
@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

}
