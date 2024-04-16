package com.ticket.booking.showsync.configuration;

import com.ticket.booking.showsync.config.UserInfoUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

//    private final JwtService jwtService;
//    private final UserDetailsService userDetailsService;
//    @Override
//    protected void doFilterInternal(
//            @NonNull HttpServletRequest request
//            , @NonNull HttpServletResponse response
//            , @NonNull FilterChain filterChain) throws ServletException, IOException {
//
//             final String authHeader = request.getHeader("Authorization");
//             final String jwt;
//             String userName=null;
//
//             if(authHeader==null || !authHeader.startsWith("Bearer")){
//                 filterChain.doFilter(request,response);
//                 return;
//             }
//
//             jwt = authHeader.substring(7);
//             try{
//                 userName = jwtService.extractUserName(jwt);
//             }catch (IllegalArgumentException e) {
//                 logger.info("Illegal Argument while fetching the username !!");
//                 e.printStackTrace();
//             } catch (ExpiredJwtException e) {
//                 logger.info("Given jwt token is expired !!");
//                 e.printStackTrace();
//             } catch (MalformedJwtException e) {
//                 logger.info("Some changed has done in token !! Invalid Token");
//                 e.printStackTrace();
//             } catch (Exception e) {
//                 e.printStackTrace();
//
//             }
//
//
//             if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null){
//                 UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
//                 if(jwtService.isTokenValid(jwt,userDetails)){
//                     UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
//                     authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                     SecurityContextHolder.getContext().setAuthentication(authToken);
//                 }
//                 filterChain.doFilter(request,response);
//
//             }
//    }
@Autowired
private JwtService jwtService;

    @Autowired
    private UserInfoUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtService.extractUsername(token);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
