package tech.salvas.eifapi.configs.security;


import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String subject;
        System.out.println("qwer1");
        // TODO : change bearer to value in properties
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("qwer2");
            filterChain.doFilter(request, response);
            return;
        }
        System.out.println("toto");
        // 7 for : "Bearer "
        jwt = authHeader.substring(7);
        subject = jwtService.extractSubjectFromToken(jwt);
        if (subject != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            AppUserDetails appUserDetails = (AppUserDetails) this.userDetailsService.loadUserByUsername(subject);
            if (jwtService.isTokenValid(jwt, appUserDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        appUserDetails,
                        null,
                        appUserDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
