package com.project.ecommerce.auth;

import com.project.ecommerce.services.auth.UserAuthService;
import io.jsonwebtoken.io.IOException;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final JwtUtil jwtUtil;

    private final UserAuthService userAuthService;



    public JWTAuthorizationFilter(@Lazy AuthenticationManager authenticationManager,
                                  JwtUtil jwtUtil,
                                  @Lazy UserAuthService authService) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.userAuthService = authService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException, java.io.IOException {
        String header = request.getHeader("Authorization");
        logger.info(request.getRequestURL()
                           .toString());
        if (header != null && header.startsWith("Bearer ")) {

            UsernamePasswordAuthenticationToken auth = getAuthentication(header.substring(7));

            if (auth == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                return;
            }

            SecurityContextHolder.getContext()
                                 .setAuthentication(auth);
        }


        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        if (jwtUtil.validateToken(token)) {
            String email = jwtUtil.extractUsername(token);
            UserDetails userDetails = userAuthService.loadUserByUsername(email);

            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        }
        return null;
    }
}