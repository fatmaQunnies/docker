// package com.shouq.postservice;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import java.io.IOException;


// public class JwtAuthenticationFilter extends OncePerRequestFilter {
// public static String t;
//     @Autowired
//     private JwtUtils jwtUtils;

//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//         String authHeader = request.getHeader("Authorization");

//         if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//             response.setStatus(HttpStatus.UNAUTHORIZED.value());
//             return;
//         }

//         String token = authHeader.substring(7);

//         if (!jwtUtils.validateJwtToken(token)) {
//             response.setStatus(HttpStatus.UNAUTHORIZED.value());
//             return;
//         }

//         filterChain.doFilter(request, response);
//     }
// }