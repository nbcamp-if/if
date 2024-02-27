package com.nbcampif.ifstagram.global.filter;

import com.nbcampif.ifstagram.domain.user.model.User;
import com.nbcampif.ifstagram.global.jwt.JwtTokenProvider;
import com.nbcampif.ifstagram.global.jwt.TokenState;
import io.jsonwebtoken.JwtException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j(topic = "JwtAuthenticationFilter")
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtTokenProvider jwtTokenProvider;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
  ) throws ServletException, IOException {
    if (isSwagger(request)) {
      filterChain.doFilter(request, response);
      return;
    }

    String tokenValue;
    String token;
    TokenState tokenState;

    try {
      tokenValue = jwtTokenProvider.getAccessTokenFromRequest(request);
      token = jwtTokenProvider.substringToken(tokenValue);
      tokenState = jwtTokenProvider.checkTokenState(token);
    } catch (EntityNotFoundException e) {
      filterChain.doFilter(request, response);
      return;
    }

    if (tokenState == TokenState.INVALID) {
      throw new JwtException("Invalid Token");
    }
    if (tokenState == TokenState.EXPIRED) {
      String accessTokenWithBearer = jwtTokenProvider.reGenerateAccessToken(token);
      jwtTokenProvider.addAccessTokenToCookie(accessTokenWithBearer, response);
    }

    setAuthentication(jwtTokenProvider.getUserFromToken(token));

    filterChain.doFilter(request, response);
  }

  public void setAuthentication(User user) {
    SecurityContext context = SecurityContextHolder.createEmptyContext();
    Authentication authentication = createAuthentication(user);
    context.setAuthentication(authentication);

    SecurityContextHolder.setContext(context);
  }

  private Authentication createAuthentication(final User user) {
    List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole()
        .getAuthority()));

    return new UsernamePasswordAuthenticationToken(user, null, authorities);
  }

  private boolean isSwagger(final HttpServletRequest request) {
    String uri = request.getRequestURI();
    return uri.contains("swagger")
        || uri.contains("api-docs")
        || uri.contains("webjars");
  }

}
