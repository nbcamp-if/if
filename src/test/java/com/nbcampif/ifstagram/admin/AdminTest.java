package com.nbcampif.ifstagram.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.nbcampif.ifstagram.domain.admin.dto.LoginRequestDto;
import com.nbcampif.ifstagram.domain.admin.dto.UserForceUpdateRequestDto;
import com.nbcampif.ifstagram.domain.post.dto.PostRequestDto;
import com.nbcampif.ifstagram.domain.post.entity.Post;
import com.nbcampif.ifstagram.domain.post.repository.PostRepository;
import com.nbcampif.ifstagram.domain.user.UserRole;
import com.nbcampif.ifstagram.domain.user.model.User;
import com.nbcampif.ifstagram.domain.user.repository.UserRepository;
import com.nbcampif.ifstagram.global.response.CommonResponse;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdminTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PostRepository postRepository;

  @LocalServerPort
  private int port;

  @Value("${admin.email}")
  private String adminEmail;

  @Value("${admin.password}")
  private String adminPassword;

  @Value("${upload.path}")
  private String filePath;

  private String token;

  @BeforeEach
  @Test
  void admin_login_success() {
    User admin = new User(1L, adminEmail, "admin", UUID.randomUUID()
        .toString(), null, UserRole.ADMIN);
    userRepository.createUser(admin);
    LoginRequestDto requestDto = new LoginRequestDto(adminEmail, adminPassword);
    String baseUrl = "http://localhost:" + port + "/api/v1/admin/login";

    ResponseEntity<CommonResponse> responseEntity = restTemplate.postForEntity(baseUrl, requestDto, CommonResponse.class);
    HttpHeaders headers = responseEntity.getHeaders();
    token = headers.getFirst(HttpHeaders.SET_COOKIE);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals("로그인 성공", responseEntity.getBody().getMessage());
  }

  @Test
  @DisplayName("관리자 로그인 실패 - 계정 권한")
  void admin_login_fail1() {
    User admin = new User(1L, adminEmail, "admin", UUID.randomUUID()
        .toString(), null, UserRole.USER);
    userRepository.createUser(admin);
    LoginRequestDto requestDto = new LoginRequestDto(adminEmail, adminPassword);
    String baseUrl = "http://localhost:" + port + "/api/v1/admin/login";

    ResponseEntity<CommonResponse> responseEntity = restTemplate.postForEntity(baseUrl, requestDto, CommonResponse.class);

    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertEquals("허용되지 않은 권한입니다.", responseEntity.getBody().getMessage());
  }

  @Test
  @DisplayName("관리자 로그인 실패 - 비밀번호 실패")
  void admin_login_fail2() {
    User admin = new User(1L, adminEmail, "admin", UUID.randomUUID()
        .toString(), null, UserRole.ADMIN);
    userRepository.createUser(admin);
    LoginRequestDto requestDto = new LoginRequestDto(adminEmail, "notpassword");
    String baseUrl = "http://localhost:" + port + "/api/v1/admin/user/{userId}";

    ResponseEntity<CommonResponse> responseEntity = restTemplate.postForEntity(baseUrl, requestDto, CommonResponse.class);

    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertEquals("허용되지 않은 권한입니다.", responseEntity.getBody().getMessage());
  }

  @Test
  void admin_user_search() {
    User user = new User(100L, "user@test.com", "user1", UUID.randomUUID()
        .toString(), null, UserRole.USER);
    userRepository.createUser(user);
    String baseUrl = "http://localhost:" + port + "/api/v1/admin/user/" + user.getUserId();

    HttpHeaders headers = new HttpHeaders();
    headers.set(HttpHeaders.COOKIE, token);

    ResponseEntity<CommonResponse> responseEntity = restTemplate.exchange(baseUrl, HttpMethod.GET, new HttpEntity<>(null, headers), CommonResponse.class);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals("조회 성공", responseEntity.getBody().getMessage());
  }

  @Test
  void admin_report_search() {
    User user = new User(100L, "user@test.com", "user1", UUID.randomUUID()
        .toString(), null, UserRole.USER);
    userRepository.createUser(user);
    String baseUrl = "http://localhost:" + port + "/api/v1/admin/report/" + user.getUserId();

    HttpHeaders headers = new HttpHeaders();
    headers.set(HttpHeaders.COOKIE, token);

    ResponseEntity<CommonResponse> responseEntity = restTemplate.exchange(baseUrl, HttpMethod.GET, new HttpEntity<>(null, headers), CommonResponse.class);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals("신고 내역 조회 성공", responseEntity.getBody().getMessage());
  }

  @Test
  void admin_user_update() {
    User user = new User(100L, "user@test.com", "user1", UUID.randomUUID()
        .toString(), null, UserRole.USER);
    userRepository.createUser(user);

    UserForceUpdateRequestDto requestDto = new UserForceUpdateRequestDto("test", "user2", null, UserRole.USER);

    HttpHeaders headers = new HttpHeaders();
    headers.set(HttpHeaders.COOKIE, token);

    String baseUrl = "http://localhost:" + port + "/api/v1/admin/user/" + user.getUserId();

    ResponseEntity<CommonResponse> responseEntity = restTemplate.exchange(baseUrl, HttpMethod.PUT, new HttpEntity<>(requestDto, headers), CommonResponse.class);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals("유저 정보 수정 성공", responseEntity.getBody().getMessage());
  }

  @Test
  void admin_post_delete() {
    Post post = new Post(new PostRequestDto("title", "content"), 100L);
    postRepository.save(post);

    HttpHeaders headers = new HttpHeaders();
    headers.set(HttpHeaders.COOKIE, token);

    String baseUrl = "http://localhost:" + port + "/api/v1/admin/post/" + post.getId();

    ResponseEntity<CommonResponse> responseEntity = restTemplate.exchange(baseUrl, HttpMethod.DELETE, new HttpEntity<>(null, headers), CommonResponse.class);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals("게시글 삭제 성공", responseEntity.getBody().getMessage());
  }

  @Test
  void admin_deleted_post_search() {
    Post post = new Post(new PostRequestDto("title", "content"), 100L);
    postRepository.save(post);
    post.delete();

    HttpHeaders headers = new HttpHeaders();
    headers.set(HttpHeaders.COOKIE, token);

    String baseUrl = "http://localhost:" + port + "/api/v1/admin/posts/deleted";

    ResponseEntity<CommonResponse> responseEntity = restTemplate.exchange(baseUrl, HttpMethod.GET, new HttpEntity<>(null, headers), CommonResponse.class);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals("삭제된 게시글 조회", responseEntity.getBody().getMessage());
  }

}
