package com.ennova_research.academy.xyzspring.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @author Alberto Ielpo
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException exc)
			throws IOException, ServletException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			System.out.println(auth);
		}

        res.setContentType("application/json;charset=UTF-8");
        res.setStatus(403);
        res.getWriter().write("{\"status\": \"Error\", \"message\": \"Access Denied\"}");
	}
}