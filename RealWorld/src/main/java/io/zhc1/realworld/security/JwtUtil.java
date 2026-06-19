package io.zhc1.realworld.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

public class JwtUtil {

    // Generamos la clave de forma segura
    private static final Key SIGNING_KEY = Keys.hmacShaKeyFor(JwtConfig.SECRET_KEY.getBytes());

    public static String generarToken(String username) {
        return Jwts.builder()
                .setSubject(username) // Probemos con setSubject, es el más compatible
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SIGNING_KEY, SignatureAlgorithm.HS256) // Firma combinada
                .compact();
    }

    public static boolean validarToken(String token) {
        try {
            Jwts.parserBuilder() // Usamos parserBuilder en lugar de parser
                    .setSigningKey(SIGNING_KEY)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String extraerUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SIGNING_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}