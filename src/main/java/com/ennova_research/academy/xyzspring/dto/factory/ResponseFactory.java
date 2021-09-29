package com.ennova_research.academy.xyzspring.dto.factory;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

/**
 * @author Alberto Ielpo
 */
public class ResponseFactory {
    
	private static final HttpHeaders jsonHttpHeaders;
	
	private static final String OK = "Ok";
	private static final String ERROR = "Error";
	
	static {
		jsonHttpHeaders = new HttpHeaders();
		jsonHttpHeaders.setContentType(MediaType.APPLICATION_JSON);
	}
	
	public static ResponseEntity<Response> jsonOkResponse(Object data) {		   	   
		return new ResponseEntity<>(new Response(OK, data), jsonHttpHeaders, HttpStatus.OK);	//+200
	}
	
	public static ResponseEntity<Response> jsonErrorResponse(Object data) {		   	   
		return new ResponseEntity<>(new Response(ERROR, data), jsonHttpHeaders, HttpStatus.OK);	//+200
	}
}
