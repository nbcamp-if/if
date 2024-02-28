package com.nbcampif.ifstagram.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.nbcampif.ifstagram.domain.admin.dto.LoginRequestDto;
import com.nbcampif.ifstagram.domain.user.UserRole;
import com.nbcampif.ifstagram.domain.user.model.User;
import com.nbcampif.ifstagram.domain.user.repository.UserRepository;
import com.nbcampif.ifstagram.global.response.CommonResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdminTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private UserRepository userRepository;

  @LocalServerPort
  private int port;

  @Value("${admin.email}")
  private String adminEmail;

  @Value("${admin.password}")
  private String adminPassword;

  @Test
  void admin_login_success() {
    User admin = new User(1L, adminEmail, "admin", null, UserRole.ADMIN);
    userRepository.createUser(admin);
    LoginRequestDto requestDto = new LoginRequestDto(adminEmail, adminPassword);
    String baseUrl = "http://localhost:" + port + "/api/v1/admin/login";

    ResponseEntity<CommonResponse> responseEntity = restTemplate.postForEntity(
      baseUrl,
      requestDto,
      CommonResponse.class
    );

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals("로그인 성공", responseEntity.getBody().getMessage());
  }

  @Test
  @DisplayName("관리자 로그인 실패 - 계정 권한")
  void admin_login_fail1() {
    User admin = new User(1L, adminEmail, "admin", null, UserRole.USER);
    userRepository.createUser(admin);
    LoginRequestDto requestDto = new LoginRequestDto(adminEmail, adminPassword);
    String baseUrl = "http://localhost:" + port + "/api/v1/admin/login";

    ResponseEntity<CommonResponse> responseEntity = restTemplate.postForEntity(
      baseUrl,
      requestDto,
      CommonResponse.class
    );

    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertEquals("허용되지 않은 권한입니다.", responseEntity.getBody().getMessage());
  }

  @Test
  @DisplayName("관리자 로그인 실패 - 비밀번호 실패")
  void admin_login_fail2() {
    User admin = new User(1L, adminEmail, "admin", null, UserRole.ADMIN);
    userRepository.createUser(admin);
    LoginRequestDto requestDto = new LoginRequestDto(adminEmail, "notpassword");
    String baseUrl = "http://localhost:" + port + "/api/v1/admin/user/{userId}";

    ResponseEntity<CommonResponse> responseEntity = restTemplate.postForEntity(
      baseUrl,
      requestDto,
      CommonResponse.class
    );

    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertEquals("허용되지 않은 권한입니다.", responseEntity.getBody().getMessage());
  }

  @Test
  @WithMockUser(roles = {"ADMIN"})
  void admin_user_search() {
    User user = new User(
      100L, "user@test.com", "user1", null, UserRole.USER
    );
    userRepository.createUser(user);
    String baseUrl = "http://localhost:" + port + "/api/v1/admin/user/" + user.getUserId();
    System.out.println(baseUrl);
    ResponseEntity<CommonResponse> responseEntity = restTemplate.getForEntity(
      baseUrl,
      CommonResponse.class
    );
    System.out.println(responseEntity);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals("조회 성공", responseEntity.getBody().getMessage());
  }
}
