package br.edu.pucminas.aluguel.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {
    private final SecretKey key;
    private final long expiration;
    public JwtService(@Value("${app.jwt.secret}") String secret,
                      @Value("${app.jwt.expiration-ms}") long expiration) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expiration = expiration;
    }
    public String gerarToken(String email, String tipo) {
        Date agora = new Date();
        return Jwts.builder().subject(email).claim("tipo", tipo)
            .issuedAt(agora).expiration(new Date(agora.getTime() + expiration))
            .signWith(key).compact();
    }
    public String extrairEmail(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token)
            .getPayload().getSubject();
    }
    public boolean validar(String token) {
        try { Jwts.parser().verifyWith(key).build().parseSignedClaims(token); return true; }
        catch (JwtException | IllegalArgumentException e) { return false; }
    }
}
