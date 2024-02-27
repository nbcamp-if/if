package com.nbcampif.ifstagram.global.handler;

import com.nbcampif.ifstagram.domain.user.model.User;
import com.nbcampif.ifstagram.global.jwt.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j(topic = "CustomLogoutHandler")
@Component
@RequiredArgsConstructor
public class CustomLogoutHandler implements LogoutHandler {

  private final JwtTokenProvider jwtTokenProvider;

  @Override
  @Transactional
  public void logout(
      HttpServletRequest request,
      HttpServletResponse response,
      Authentication authentication
  ) {
    String tokenValue = jwtTokenProvider.getAccessTokenFromRequest(request);
    String token = jwtTokenProvider.substringToken(tokenValue);
    User user = jwtTokenProvider.getUserFromToken(token);

    jwtTokenProvider.expireToken(user.getUserId());
  }

}
