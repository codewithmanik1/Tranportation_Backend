package com.om.operations.security.filter;

import com.om.operations.security.JwtUtils;
import com.om.operations.security.impl.OmUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class AuthTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private OmUserDetails userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("request " + request.getRequestURI());
        String jwt = request.getHeader("Authorization");
        System.out.println("Filter token " + jwt);
//        if (WebSecurityConfig.AUTH_WHITELIST.contains(request.getRequestURI())) {
//            filterChain.doFilter(request, response);
//            return;
//        }



        if ((jwt != null && jwt.startsWith("Bearer "))){
            // Remove the "Bearer " prefix and any extra spaces
            jwt = jwt.substring(7).trim();
            System.out.println("Trimmed Token: [" + jwt + "]");

            // Validate the token
            if (jwtUtils.validateJwtToken(jwt)) {
                try {
                    String username = jwtUtils.getUsernameFromToken(jwt);
                    System.out.println("Filter username " + username);
                    SecurityContext context = SecurityContextHolder.createEmptyContext();
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    context.setAuthentication(authentication);
                    SecurityContextHolder.setContext(context);
                } catch (Exception e) {
                    logger.error("Cannot set user authentication: {}", e);
                }
            }
        }

        filterChain.doFilter(request, response);
    }

//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//        // Exclude paths starting with /art
//        return request.getServletPath().startsWith("/api/auth");
//    }
}