package com.nbcampif.ifstagram.integration;

import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.nbcampif.ifstagram.domain.user.model.User;
import com.nbcampif.ifstagram.domain.user.repository.UserRepository;
import com.nbcampif.ifstagram.global.common.TestValues;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class UserIntegrationTest extends TestValues {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private UserRepository userRepository;

  @BeforeEach
  void setUp() {
    userRepository.createUser(TEST_USER1);
    userRepository.createUser(TEST_USER2);
  }

  @Nested
  @DisplayName("내 정보 조회")
  class myPage {

    @Test
    void success() throws Exception {
      // given
      User user = TEST_USER1;

      // when
      mockMvc.perform(get("/api/v1/users/my-page"))

          // then
          .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
          .andExpect(jsonPath("$.message").value(endsWith("성공")))
          .andExpect(jsonPath("$.data.email").value(user.getEmail()))
          .andExpect(jsonPath("$.data.nickname").value(user.getNickname()))
          .andExpect(jsonPath("$.data.profileImage").value(user.getProfileImage()))
          .andExpect(jsonPath("$.data.reportedCount").value(user.getReportedCount()));
    }

  }

  @Nested
  @DisplayName("팔로우")
  class follow {

    @Test
    void success() throws Exception {
      // given
      User user = TEST_USER1;
      User toUser = TEST_USER2;

      // when
      mockMvc.perform(post("/api/v1/users/" + toUser.getUserId() + "/follow"))

          // then
          .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
          .andExpect(jsonPath("$.message").value(endsWith("성공")));
    }

  }

  @Nested
  @DisplayName("내 정보 수정")
  class updateUser {

    @Test
    void success() throws Exception {
      // given
      User user = TEST_USER1;

      // when
      mockMvc.perform(put("/api/v1/users/my-page").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
              .content(("{\n" + "  \"introduction\": \"%s\",\n".formatted(TEST_UPDATE_INTRODUCTION)
                        + "  \"nickname\": \"%s\",\n".formatted(TEST_UPDATE_NICKNAME)
                        + "  \"profileImage\": \"%s\"\n".formatted(TEST_UPDATE_PROFILEIMAGE) + "}")))

          // then
          .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
          .andExpect(jsonPath("$.message").value(endsWith("성공")))
          .andExpect(jsonPath("$.data.introduction").value(TEST_UPDATE_INTRODUCTION))
          .andExpect(jsonPath("$.data.nickname").value(TEST_UPDATE_NICKNAME))
          .andExpect(jsonPath("$.data.profileImage").value(TEST_UPDATE_PROFILEIMAGE));
    }

  }

  @Nested
  @DisplayName("회원 신고")
  class reportUser {

    @Test
    void success() throws Exception {
      // given
      Long reportedUserId = TEST_USER2.getUserId();

      // when
      mockMvc.perform(post("/api/v1/users/reports/" + reportedUserId))

          // then
          .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
          .andExpect(jsonPath("$.message").value(endsWith("신고되었습니다")));
    }

  }

}
