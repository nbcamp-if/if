package com.nbcampif.ifstagram.domain.admin.service;

import com.nbcampif.ifstagram.domain.admin.dto.LoginRequestDto;
import com.nbcampif.ifstagram.domain.user.UserRole;
import com.nbcampif.ifstagram.domain.user.model.User;
import com.nbcampif.ifstagram.domain.user.repository.UserRepository;
import com.nbcampif.ifstagram.global.jwt.JwtTokenProvider;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@DependsOn("userRepository")
public class AdminService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${admin.password}")
    private String verifyPassword;

    @PostConstruct
    public void createAdminAccount() {
        boolean existsAdmin = userRepository.existsByRole(UserRole.ADMIN);
        if (!existsAdmin) {
            User admin = new User(
                1L,
                "admin@example.com",
                "admin",
                "/img/admin.png",
                UserRole.ADMIN
            );
            userRepository.createUser(admin);
        }
    }

    public void login(LoginRequestDto requestDto, HttpServletResponse response) {
        String password = requestDto.getPassword();
        User user = userRepository.findByEmail(requestDto.getEmail());
        if (verifyPassword.equals(password) && user.getRole().equals(UserRole.ADMIN)) {
            String accessToken = jwtTokenProvider.generateAccessToken(user.getUserId(), user.getRole().getAuthority());
            jwtTokenProvider.addAccessTokenToCookie(accessToken, response);
        }
    }
}
