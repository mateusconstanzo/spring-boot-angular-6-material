package br.com.events.auth.service.impl;

import br.com.events.auth.SecurityConstants;
import br.com.events.auth.service.JWTService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JWTServiceImpl implements JWTService {

    @Override
    public String generate(String id) {

        return Jwts.builder()
                .setSubject(id)
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET.getBytes())
                .compact();

    }

    @Override
    public Optional<String> valid(String token) {

        String user = parser(token);

        return Optional.of(user);

    }

    private String parser(String token) {

        return Jwts.parser()
                .setSigningKey(SecurityConstants.SECRET.getBytes())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

    }

}
