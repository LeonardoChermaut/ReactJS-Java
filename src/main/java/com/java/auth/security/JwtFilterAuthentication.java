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
	public static final int TOKEN_EXPIRACAO = 1000_0000;

	public static final String TOKEN_SENHA = "509b8615-b833-4f60-bacc-be22c17b4dbe";

	private final AuthenticationManager authManager;

	public JwtFilterAuthentication(AuthenticationManager authManager) {
		this.authManager = authManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			UserModel usuario = new ObjectMapper().readValue(request.getInputStream(), UserModel.class);

			return authManager.authenticate(
					new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getSenha(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException("Falha ao autenticar o usuario", e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
											Authentication authResult) throws IOException, ServletException {
		UserDetailsImpl usuarioData = (UserDetailsImpl) authResult.getPrincipal();

		String token = JWT.create().withSubject(usuarioData.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRACAO))
				.sign(Algorithm.HMAC512(TOKEN_SENHA));

		response.getWriter().write(token );
		response.getWriter().flush();

	}
}