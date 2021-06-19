package com.app.statsservice.security;

import com.app.statsservice.service.UserDetailsServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
* This class will be called whenever we call an endpoint in the app
* */
public class JWTAuthenticationFilter extends OncePerRequestFilter {

  private JWTProvider jwtProvider;
  private UserDetailsServiceImpl userDetailsServiceImpl;

  public JWTAuthenticationFilter(JWTProvider jwtProvider, UserDetailsServiceImpl userDetailsService) {
      this.jwtProvider =  jwtProvider;
      this.userDetailsServiceImpl = userDetailsService;
  }

  public JWTAuthenticationFilter() {

  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {
    String jwtToken = getJwtFromRequest(request);

    if(StringUtils.hasText(jwtToken) && jwtProvider.validateToken(jwtToken)) {
      String username = jwtProvider.getUsernameFromJWT(jwtToken);

      UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
          userDetails, null, userDetails.getAuthorities());
      authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

      SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    filterChain.doFilter(request, response);
  }

  private String getJwtFromRequest(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return bearerToken;
  }
}
