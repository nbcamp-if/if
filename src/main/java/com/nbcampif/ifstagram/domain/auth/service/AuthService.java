package com.nbcampif.ifstagram.domain.auth.service;

import com.nbcampif.ifstagram.domain.user.model.User;
import com.nbcampif.ifstagram.domain.user.repository.UserRepository;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j(topic = "AUTH_SERVICE")
@RequiredArgsConstructor
@Service
public class AuthService extends DefaultOAuth2UserService {

  private final UserRepository userRepository;

  @Override
  @Transactional
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    OAuth2User oAuth2User = super.loadUser(userRequest);
    Map<String, Object> attributes = oAuth2User.getAttributes();
    Long userId = (Long) attributes.get("id");

    User user;
    Optional<User> savedUser = userRepository.findUser(userId);

    if (savedUser.isEmpty()) {
      Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
      Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
      String email = (String) kakaoAccount.get("email");
      String nickname = (String) profile.get("nickname");
      String profileImage = (String) profile.get("profile_image_url");
      user = new User(userId, email, nickname, profileImage);

      userRepository.createUser(user);
    } else {
      user = savedUser.get();
    }

    return user;
  }

}
