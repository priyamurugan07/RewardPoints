package com.sample.Reward.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sample.Reward.Exception.RewardException;
import com.sample.Reward.service.CustomerService;
import com.sample.Reward.service.CustomerServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private CustomerService customerService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = request.getHeader("Authorization");
		try {
		if(token != null && token.startsWith("Bearer ")) {
			token = token.substring(7);
			String email = jwtUtil.extractEmail(token);
			
			if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				//UserDetails userDetails = customerService.loadUserByUsername(email);
				UserDetails userDetails = User.builder().username(email).password("").authorities(Collections.emptyList()).build();
				if(jwtUtil.isTokenValid(token)) {
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
			}
		}
		 filterChain.doFilter(request, response);
		 
	}catch (ExpiredJwtException e) {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getWriter().write("JWT has expired");
	}catch (MalformedJwtException | SignatureException e) {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getWriter().write("Invalid JWT token");
	}catch (Exception e) {
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		response.getWriter().write("Invalid JWT token");
	}
	
	}
}
