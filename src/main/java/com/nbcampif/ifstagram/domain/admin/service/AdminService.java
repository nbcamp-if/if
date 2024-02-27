package com.nbcampif.ifstagram.domain.admin.service;

import com.nbcampif.ifstagram.domain.admin.dto.LoginRequestDto;
import com.nbcampif.ifstagram.domain.user.UserRole;
import com.nbcampif.ifstagram.domain.user.dto.UserResponseDto;
import com.nbcampif.ifstagram.domain.user.model.User;
import com.nbcampif.ifstagram.domain.user.repository.UserRepository;
import com.nbcampif.ifstagram.global.exception.NotFoundUserException;
import com.nbcampif.ifstagram.global.exception.PermissionNotException;
import com.nbcampif.ifstagram.global.jwt.JwtTokenProvider;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

  private final UserRepository userRepository;
  private final JwtTokenProvider jwtTokenProvider;

  @Value("${admin.email}")
  private String adminEmail;

  @Value("${admin.password}")
  private String verifyPassword;

  @PostConstruct
  public void createAdminAccount() {
    boolean existsAdmin = userRepository.existsByRole(UserRole.ADMIN);
    if (!existsAdmin) {
      User admin = new User(
          1L,
          adminEmail,
          "admin",
          null,
          UserRole.ADMIN
      );
      userRepository.createUser(admin);
    }
  }

  public void login(LoginRequestDto requestDto, HttpServletResponse response) {
    String password = requestDto.getPassword();
    User user = userRepository.findByEmail(requestDto.getEmail()).orElseThrow(() ->
        new NotFoundUserException("해당 유저는 존재하지 않습니다.")
    );
    if (!verifyPassword.equals(password) || !user.getRole().equals(UserRole.ADMIN)) {
      throw new PermissionNotException("허용되지 않은 권한입니다.");
    }

    String accessToken = jwtTokenProvider.generateAccessToken(
        user.getUserId(), user.getRole().getAuthority()
    );
    String refreshToken = jwtTokenProvider.generateRefreshToken(
        user.getUserId(), user.getRole().getAuthority());

    jwtTokenProvider.addAccessTokenToCookie(accessToken, response);
    }

    public UserResponseDto searchUser(Long userId) {
      User user = userRepository.findUser(userId).orElseThrow(() ->
            new NotFoundUserException("해당 유저는 존재하지 않습니다.")
        );

      UserResponseDto responseDto = UserResponseDto.of(user);
        return responseDto;
    }

    public List<ReportReponseDto> searchReport(Long reportId) {
        return null;
    }

    @Transactional
    public void updateUser(Long userId, UserUpdateRequestDto requestDto) {
      User user = userRepository.findUser(userId).orElseThrow(() ->
        new NotFoundUserException("해당 유저는 존재하지 않습니다.")
      );

    userRepository.updateUser(requestDto, user);
  }

  @Transactional
  public void updatePost(Long postId, PostRequestDto requestDto, MultipartFile image)
    throws IOException {

    Post post = postRepository.findById(postId)
      .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
    postImageService.updateImage(post, image);
    post.updatePost(requestDto);
  }
}
