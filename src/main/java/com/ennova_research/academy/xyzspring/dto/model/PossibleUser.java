package com.ennova_research.academy.xyzspring.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Alberto Ielpo
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PossibleUser {

	public PossibleUser() {
		
	}
	
	@JsonProperty(value = "name")
	public String name;
	
	@JsonProperty(value = "city")
	public String city;
	
	@JsonProperty(value = "email")
	public String email;
	
}
