package com.nbcampif.ifstagram.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.nbcampif.ifstagram.domain.admin.dto.LoginRequestDto;
import com.nbcampif.ifstagram.domain.user.UserRole;
import com.nbcampif.ifstagram.domain.user.model.User;
import com.nbcampif.ifstagram.domain.user.repository.UserRepository;
import com.nbcampif.ifstagram.global.response.CommonResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

  @BeforeEach
  public void setUp() {
    User admin = new User(1L, adminEmail, "admin", null, UserRole.ADMIN);
    userRepository.createUser(admin);
  }

  @Test
  void adminLogin() {
    LoginRequestDto requestDto = new LoginRequestDto(adminEmail, adminPassword);
    String baseUrl = "http://localhost:" + port + "/ap1/v1/admin/login";

    ResponseEntity<CommonResponse> responseEntity = restTemplate.postForEntity(
      baseUrl,
      requestDto,
      CommonResponse.class
    );

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//    assertEquals("로그인 성공", responseEntity.getBody().getMessage());
  }
}
