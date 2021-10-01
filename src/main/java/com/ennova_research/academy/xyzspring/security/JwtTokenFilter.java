package com.ennova_research.academy.xyzspring.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
/**
 * @author Alberto Ielpo
 */
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

	@Autowired
	JwtTokenManager jwtTokenManager;
	
	@Autowired
	UserDetailsService userDetailsServiceImpl;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader = request.getHeader("Authorization");
		if(StringUtils.hasText(authHeader)) {
			if(authHeader.startsWith("Bearer ")) {
				String bearer = authHeader.substring(7);
				String username = jwtTokenManager.extractUsername(bearer);
				if(StringUtils.hasText(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
					UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
					if(jwtTokenManager.isValid(bearer, userDetails)) {
						UsernamePasswordAuthenticationToken at = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
						at.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						SecurityContextHolder.getContext().setAuthentication(at);
					}
				}
			}
		}
		
		filterChain.doFilter(request, response);
		
	}

}
