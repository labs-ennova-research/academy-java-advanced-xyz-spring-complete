package com.ennova_research.academy.xyzspring.security;

import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Controller
public class JwtTokenManager {

	/**
	 * 
	 * @param username
	 * @return
	 */
	public String createToken(String username) {
		try {
			Date oneHour = new Date(new Date().getTime()+1000*60*60);
			Algorithm algorithm = Algorithm.HMAC256("thisIsMySecretKey");
		    String token = JWT.create()
		    		.withIssuer("xyz")
		    		.withExpiresAt(oneHour)
		    		.withIssuedAt(new Date())
		    		.withSubject(username)
		    		.sign(algorithm);
		    return token;
		} catch (JWTCreationException exception){
			return null;
		}
	}
	
	/**
	 * 
	 * @param token
	 * @return
	 */
	public String extractUsername(String token) {
		try {
		   DecodedJWT jwt = JWT.decode(token);
		   return jwt.getSubject();
		} catch (JWTDecodeException exception){
			return null;
		}
	}
	
	/**
	 * 
	 * @param token
	 * @return
	 */
	public boolean isValidToken(String token) {
		try {
		    DecodedJWT jwt = JWT.decode(token);
		    return jwt.getExpiresAt().after(new Date());
		} catch (JWTDecodeException exception){
			return false;
		}
	}
	
	/**
	 * 
	 * @param token
	 * @param userDetails
	 * @return
	 */
	public boolean isValid(String token, UserDetails userDetails) {
		if(isValidToken(token)) {
			return userDetails.getUsername().equals(extractUsername(token));
		}
		return false;
	}
}
