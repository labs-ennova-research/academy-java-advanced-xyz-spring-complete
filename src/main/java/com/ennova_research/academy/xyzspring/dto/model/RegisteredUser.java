package com.ennova_research.academy.xyzspring.dto.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Alberto Ielpo
 */
public class RegisteredUser {

	public RegisteredUser() {
		
	}
	
	@JsonProperty(value = "name")
	public String name;
	
	@JsonProperty(value = "surname")
	public String surname;
	
}
