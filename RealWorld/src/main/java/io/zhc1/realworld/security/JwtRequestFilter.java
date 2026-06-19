package io.zhc1.realworld.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        // 1. Rutas públicas: Saltar filtro
        // Cambia esto en tu JwtRequestFilter.java
        if (path.contains("/api/v1/auth/")) {
            chain.doFilter(request, response);
            return;

        }

        final String header = request.getHeader("Authorization");

        // 2. Rutas privadas: Validar
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            if (JwtUtil.validarToken(token)) {
                String username = JwtUtil.extraerUsername(token);
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(auth);
                chain.doFilter(request, response);
                return;
            }
        }

        // 3. Si llega aquí sin token en ruta privada, error 401
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido o ausente");
        // Nota: NO llames a chain.doFilter aquí porque estamos bloqueando la petición
    } // <--- ESTA LLAVE ES VITAL: Cierra el método doFilterInternal
} // <--- ESTA LLAVE CIERRA LA CLASE