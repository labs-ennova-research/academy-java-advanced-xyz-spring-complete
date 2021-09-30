package com.ennova_research.academy.xyzspring.dto.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Alberto Ielpo
 */
public class RegisteredUser {

	public RegisteredUser() {
		
	}
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty(value = "id")
	public Long id;
	
	@JsonProperty(value = "name")
	public String name;
	
	@JsonProperty(value = "surname")
	public String surname;
	
}
