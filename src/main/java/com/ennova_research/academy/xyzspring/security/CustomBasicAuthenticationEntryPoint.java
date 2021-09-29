package com.ennova_research.academy.xyzspring.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
/**
 * @author Alberto Ielpo
 */
public class CustomBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    @Override
    public void commence(final HttpServletRequest request, 
    		final HttpServletResponse response, 
    		final AuthenticationException authException) throws IOException {
    	
    	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    	response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName() + "");
        response.addHeader("Content-Type", "Application/Json");
        PrintWriter writer = response.getWriter();
        writer.println("{\"status\": \"Error\", \"message\": \"Not authorized\"}");
    }
    
    @Override
    public void afterPropertiesSet() {
        setRealmName("MY_TEST_REALM");
        super.afterPropertiesSet();
    }
}