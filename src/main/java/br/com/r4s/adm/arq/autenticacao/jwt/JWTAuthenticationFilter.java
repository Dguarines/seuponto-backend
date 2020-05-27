package br.com.r4s.adm.arq.autenticacao.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.r4s.adm.arq.dominio.Usuario;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;

	private JWTUtil jwtUtil;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
        setFilterProcessesUrl(JWTConstants.AUTH_LOGIN_URL);
    }
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
    	Usuario usuario;
		try {
			usuario = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword());
	        return authenticationManager.authenticate(authenticationToken);
		} catch (IOException e) {
			respostaNaoAtorizadoIOError(response);
			e.printStackTrace();
			return null;
		}
	}

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain filterChain, Authentication authentication) throws JsonProcessingException, IOException {
        Usuario usuario = ((Usuario) authentication.getPrincipal());
		String token = jwtUtil.generateJsonWebToken(usuario);
        response.getWriter().write("{\"token\": \"" + token + "\"}");
	}

	private void respostaNaoAtorizadoIOError(HttpServletResponse response) {
		try {
			response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
}
