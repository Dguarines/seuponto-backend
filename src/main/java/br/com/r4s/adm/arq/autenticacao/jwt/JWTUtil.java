package br.com.r4s.adm.arq.autenticacao.jwt;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import br.com.r4s.adm.arq.dominio.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTUtil {

    public String generateJsonWebToken(Usuario usuario) {
        List<String> roles = usuario.getAuthorities()
					                .stream()
					                .map(GrantedAuthority::getAuthority)
					                .collect(Collectors.toList());

        byte signingKey[] = JWTConstants.JWT_SECRET.getBytes();

        return Jwts.builder()
                   .signWith(SignatureAlgorithm.HS512, signingKey)
                   .setHeaderParam("typ", JWTConstants.TOKEN_TYPE)
                   .setIssuer(JWTConstants.TOKEN_ISSUER)
                   .setAudience(JWTConstants.TOKEN_AUDIENCE)
                   .setSubject(usuario.getUsername())
                   .setExpiration(new Date(System.currentTimeMillis() + 864000000))
                   .claim("rol", roles)
                   .compact();
    }
}