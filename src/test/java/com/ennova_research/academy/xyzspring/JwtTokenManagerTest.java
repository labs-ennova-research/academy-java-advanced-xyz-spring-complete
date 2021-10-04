package com.ennova_research.academy.xyzspring;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import com.ennova_research.academy.xyzspring.dao.model.BoUserRoleType;
import com.ennova_research.academy.xyzspring.security.JwtTokenManager;
import com.ennova_research.academy.xyzspring.security.UserDetailsImpl;

/**
 * @author Alberto Ielpo
 */
public class JwtTokenManagerTest {

	private JwtTokenManager jwtTokenManager;
	
	@Before
	public void before() {
		jwtTokenManager = new JwtTokenManager();
	}
	
    @Test
	public void testCreateToken(){
    	String token = jwtTokenManager.createToken("username");
    	Assert.hasText(token, "token must have text");
	}
    
    @Test
	public void testIsValid(){
    	String token = jwtTokenManager.createToken("username");
    	Assert.isTrue(jwtTokenManager.isValidToken(token), "Token must be valid");
    }
    
    @Test
	public void testIsNotValid(){
    	String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VybmFtZSIsImlzcyI6Inh5eiIsImV4cCI6MTYzMzM1Mjk1MywiaWF0IjoxNjMzMzQ5MzUzfQ.fdeVrDNfdoRA-UeojKPp6AwjLgfSffMHjgspPfuEYPb";
    	Assert.isTrue(!jwtTokenManager.isValidToken(token), "Token must be not valid");
    }
    
    @Test
	public void testExtractUsername(){
    	String token = jwtTokenManager.createToken("username.test");
    	Assert.isTrue(jwtTokenManager.extractUsername(token).equals("username.test"), "Username must match");
    }
    
    @Test
	public void testIsValidWithUserDetails(){
    	var userDetails = new UserDetailsImpl("username.test", "pippo", BoUserRoleType.ADMIN);
    	String token = jwtTokenManager.createToken("username.test");
    	Assert.isTrue(jwtTokenManager.isValid(token, userDetails), "Token must be valid");
    }
    
    @Test
	public void testIsNotValidWithUserDetails(){
    	var userDetails = new UserDetailsImpl("pippo.test", "pippo", BoUserRoleType.ADMIN);
    	String token = jwtTokenManager.createToken("username.test");
    	Assert.isTrue(!jwtTokenManager.isValid(token, userDetails), "Token must not be valid");
    }
	
}
