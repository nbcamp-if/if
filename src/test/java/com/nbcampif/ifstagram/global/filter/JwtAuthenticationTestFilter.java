package com.nbcampif.ifstagram.global.filter;

import com.nbcampif.ifstagram.domain.user.model.User;
import com.nbcampif.ifstagram.global.common.TestValues;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * FAKE JwtAuthenticationFilter for testing
 */
@Component
public class JwtAuthenticationTestFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
  ) throws ServletException, IOException {
    setAuthentication(TestValues.TEST_USER1);

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

}

