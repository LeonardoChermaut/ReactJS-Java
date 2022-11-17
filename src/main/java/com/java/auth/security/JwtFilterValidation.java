package com.java.auth.security;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.java.auth.service.UserDetailServiceImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JwtFilterValidation extends BasicAuthenticationFilter {

    public static final String AUTH = "Authorization";
    public static final String BEARER = "Bearer ";
    private final UserDetailServiceImpl userServiceDetail;

    public JwtFilterValidation(AuthenticationManager authenticationManager, UserDetailServiceImpl userService) {
        super(authenticationManager);
        this.userServiceDetail = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader(AUTH);

        if (header == null) {
            chain.doFilter(request, response);
            return;
        }

        if (!header.startsWith(BEARER)) {
            chain.doFilter(request, response);
            return;
        }

        String token = header.replace(BEARER, "");
        UsernamePasswordAuthenticationToken authenticationToken = getAuthToken(token);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthToken(String token) {

        String user = JWT.require(Algorithm.HMAC512(JwtFilterAuthentication.TOKEN_SENHA))
                .build()
                .verify(token)
                .getSubject();

        if (user == null) {
            return null;
        }
        UserDetails userDetail = userServiceDetail.loadUserByUsername(user);

        return new UsernamePasswordAuthenticationToken(userDetail, null, new ArrayList<>());
    }
}