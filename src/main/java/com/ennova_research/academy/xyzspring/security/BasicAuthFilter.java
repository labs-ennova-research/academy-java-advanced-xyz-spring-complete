package com.ennova_research.academy.xyzspring.security;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
/**
 * @author Alberto Ielpo
 */
@Component
public class BasicAuthFilter extends OncePerRequestFilter {

	@Autowired
	JwtTokenManager jwtTokenManager;
	
	@Autowired
	UserDetailsService userDetailsServiceImpl;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		try {
			String authHeader = request.getHeader("Authorization");
			if(StringUtils.hasText(authHeader)) {
				if(authHeader.startsWith("Basic ")) {
					String bearer = authHeader.substring(6);
				    byte[] credDecoded = Base64.getDecoder().decode(bearer);
				    String credentials = new String(credDecoded, StandardCharsets.UTF_8);
				    final String[] values = credentials.split(":", 2);
					String username = values[0];
					if(StringUtils.hasText(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
						UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
						if(userDetails.getUsername().equals(username)) {
							if(passwordEncoder.matches(values[1], userDetails.getPassword())) {
								UsernamePasswordAuthenticationToken at = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
								at.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
								SecurityContextHolder.getContext().setAuthentication(at);
							}
						}				
					}
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}

		
		filterChain.doFilter(request, response);
		
	}

}
