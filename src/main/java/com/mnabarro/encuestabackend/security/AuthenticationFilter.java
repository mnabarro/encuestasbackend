package com.mnabarro.encuestabackend.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mnabarro.encuestabackend.models.requests.UserLoginRequestModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication (HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {

            UserLoginRequestModel userLoginRequestModel = new ObjectMapper().readValue(request.getInputStream(), UserLoginRequestModel.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginRequestModel
                                        .getEmail(), userLoginRequestModel.getPassword(), new ArrayList<>()));

        }catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {

        String email = ( (User) authentication.getPrincipal()).getUsername();

        String token = Jwts.builder()
                            .setSubject(email).setExpiration(new Date(System.currentTimeMillis() +  SecurityConstants.EXPIRATION_DATE))
                            .signWith(SignatureAlgorithm.HS512,SecurityConstants.getTokenSecret()).compact();

        String data = new ObjectMapper().writeValueAsString(Map.of("token", SecurityConstants.TOKEN_PREFIX + token));

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(data);
        response.flushBuffer();
    }
}
