package com.nbcampif.ifstagram.domain.admin.service;

import com.nbcampif.ifstagram.domain.user.UserRole;
import com.nbcampif.ifstagram.domain.user.model.User;
import com.nbcampif.ifstagram.domain.user.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@DependsOn("userRepository")
public class AdminService {

    private final UserRepository userRepository;

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
}
