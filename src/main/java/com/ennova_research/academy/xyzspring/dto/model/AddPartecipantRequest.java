package com.ennova_research.academy.xyzspring.dto.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Alberto Ielpo
 */
public class AddPartecipantRequest {

	@JsonProperty(value = "idRegisteredUser")
	public long idRegisteredUser;
	
	@JsonProperty(value = "idCourse")
	public long idCourse;
	
}
