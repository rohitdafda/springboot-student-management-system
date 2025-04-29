package com.rohitdafda.springbootstudentmanagementsystem.providers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class StudentAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService studentDetailsService;

    public StudentAuthenticationProvider(@Qualifier("studentDetailsServiceImpl") UserDetailsService studentDetailsService) {
        this.studentDetailsService = studentDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        String studentCode = authentication.getName();
        String dob = authentication.getCredentials().toString(); // plain text DOB

        UserDetails user = studentDetailsService.loadUserByUsername(studentCode);
        if (!dob.equals(user.getPassword())) { // dob stored as plain text
            throw new BadCredentialsException("Invalid student credentials");
        }

        return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}

