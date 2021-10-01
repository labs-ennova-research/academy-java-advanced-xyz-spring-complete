package com.ennova_research.academy.xyzspring.dto.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Alberto Ielpo
 */
public class LoginRequest {

	@JsonProperty(value = "username")
	public String username;
	
	@JsonProperty(value = "password")
	public String password;
	
}
