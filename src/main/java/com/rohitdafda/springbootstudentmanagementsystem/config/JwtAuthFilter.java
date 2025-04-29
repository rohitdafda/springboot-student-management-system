package com.rohitdafda.springbootstudentmanagementsystem.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rohitdafda.springbootstudentmanagementsystem.services.UserDetailsServiceImpl;
import com.rohitdafda.springbootstudentmanagementsystem.services.StudentDetailsServiceImpl;
import com.rohitdafda.springbootstudentmanagementsystem.helpers.JwtHelper;
import com.rohitdafda.springbootstudentmanagementsystem.controllers.dto.ApiErrorResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

  private final UserDetailsServiceImpl userDetailsService;
  private final StudentDetailsServiceImpl studentDetailsService;
  private final ObjectMapper objectMapper;

  public JwtAuthFilter(UserDetailsServiceImpl userDetailsService,
                       StudentDetailsServiceImpl studentDetailsService,
                       ObjectMapper objectMapper) {
    this.userDetailsService = userDetailsService;
    this.studentDetailsService = studentDetailsService;
    this.objectMapper = objectMapper;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
          throws ServletException, IOException {
    try {
      String authHeader = request.getHeader("Authorization");

      String token = null;
      String username = null;
      String role = null;

      if (authHeader != null && authHeader.startsWith("Bearer ")) {
        token = authHeader.substring(7);
        username = JwtHelper.extractUsername(token);
        role = JwtHelper.extractRole(token);
      }

      if (token == null) {
        filterChain.doFilter(request, response);
        return;
      }

      if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails;

        if ("ADMIN".equals(role)) {
          userDetails = userDetailsService.loadUserByUsername(username);
        } else if ("STUDENT".equals(role)) {
          userDetails = studentDetailsService.loadUserByUsername(username);
        } else {
          throw new AccessDeniedException("Invalid user role");
        }

        if (JwtHelper.validateToken(token, userDetails)) {
          UsernamePasswordAuthenticationToken authToken =
                  new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
          authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(authToken);
        }
      }

      filterChain.doFilter(request, response);
    } catch (AccessDeniedException e) {
      ApiErrorResponse errorResponse = new ApiErrorResponse(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
      response.setStatus(HttpServletResponse.SC_FORBIDDEN);
      response.getWriter().write(toJson(errorResponse));
    }
  }

  private String toJson(ApiErrorResponse response) {
    try {
      return objectMapper.writeValueAsString(response);
    } catch (Exception e) {
      return "";
    }
  }
}
