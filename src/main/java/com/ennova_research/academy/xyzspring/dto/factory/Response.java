package com.ennova_research.academy.xyzspring.dto.factory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * @author Alberto Ielpo
 */
public class Response {
	
	public Response() {
		
	}
	
	public Response(String status, Object message) {
		this.status = status;
		this.message = message;
	}

	@JsonProperty(value = "status")
	public String status;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty(value = "message")
	public Object message;
	
}
