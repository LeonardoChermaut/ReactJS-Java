package com.java.auth.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.java.auth.model.UserModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JwtFilterAuthentication extends UsernamePasswordAuthenticationFilter {

	public static final int EXPIRED_TOKEN = 1000_0000;
	public static final String TOKEN_PASSWORD = "509b8615-b833-4f60-bacc-be22c17b4dbe";
	private final AuthenticationManager authManager;
	public JwtFilterAuthentication(AuthenticationManager authManager) {
		this.authManager = authManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			UserModel user = new ObjectMapper()
					.readValue(request.getInputStream(), UserModel.class);
			return authManager.authenticate(new UsernamePasswordAuthenticationToken(
					user.getEmail(),
					user.getSenha(),
					new ArrayList<>()));
		} catch (IOException e) {
			throw new AuthenticationException("Falha ao autenticar o usuário", e.getCause()) {
			};
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request,
											HttpServletResponse response,
											FilterChain chain,
											Authentication authResult) throws IOException, ServletException {

		UserDetailsImpl userData = (UserDetailsImpl) authResult.getPrincipal();
		String token = JWT.create().withSubject(userData.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRED_TOKEN))
				.sign(Algorithm.HMAC512(TOKEN_PASSWORD));
		response.getWriter().write(token );
		response.getWriter().flush();
	}
}