package com.nbcampif.ifstagram.global.config;

import com.nbcampif.ifstagram.domain.user.UserRole;
import com.nbcampif.ifstagram.global.filter.JwtAuthenticationFilter;
import com.nbcampif.ifstagram.global.handler.OAuth2SuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configurable
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final DefaultOAuth2UserService authService;
  private final OAuth2SuccessHandler oAuth2SuccessHandler;

  @Bean
  protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
    // disable csrf
    http.csrf(AbstractHttpConfigurer::disable);
    // disable session
    http.sessionManagement(
        management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    // config jwt filter
    http.authorizeHttpRequests(request ->
        request.requestMatchers(PathRequest.toStaticResources().atCommonLocations())
            .permitAll() // resources 접근 허용 설정
            .requestMatchers("/", "/api/v1/auth/**", "/oauth2/**", "/api/v1/admin/login")
            .permitAll() // 인증 관련.requestMatchers("/api/v1/admin/**")
        .hasRole(UserRole
            .ADMIN.name())
            .requestMatchers("/v3/**", "/swagger-ui/**")
            .permitAll() // swagger
            .anyRequest()
            .authenticated()
    );
    // config oauth2 filter
    http.oauth2Login(oauth2 -> oauth2.authorizationEndpoint(authorization -> authorization.baseUri("/api/v1/auth/login"))
        .redirectionEndpoint(
                redirection -> redirection.baseUri("/api/v1/auth/login/oauth2/callback/*"))
        .userInfoEndpoint(userInfo -> userInfo.userService(authService))
        .successHandler(oAuth2SuccessHandler));
    // add jwt filter
    http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

}
