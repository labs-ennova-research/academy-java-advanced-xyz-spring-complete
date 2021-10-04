package com.ennova_research.academy.xyzspring.security;

import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

/**
 * @author Alberto Ielpo
 */
@Controller
public class JwtTokenManager {

	private static final String SECRET = "thisIsMySecretKey";
	private static final String ISSUER = "xyz";
	
	/**
	 * 
	 * @param username
	 * @return
	 */
	public String createToken(String username) {
		try {
			Date oneHour = new Date(new Date().getTime()+1000*60*60);
			Algorithm algorithm = Algorithm.HMAC256(SECRET);
		    String token = JWT.create()
		    		.withIssuer(ISSUER)
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
		    Algorithm algorithm = Algorithm.HMAC256(SECRET);
		    JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
		    DecodedJWT jwt = verifier.verify(token);
		    return jwt.getExpiresAt().after(new Date());
		} catch (Exception exception){
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
