package com.ennova_research.academy.xyzspring.controller;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ennova_research.academy.xyzspring.dto.factory.Response;
import com.ennova_research.academy.xyzspring.dto.factory.ResponseFactory;
import com.ennova_research.academy.xyzspring.dto.model.AddPartecipantRequest;

/**
 * @author Alberto Ielpo
 */
@RestController
@RequestMapping(value = "${url.api}/tp/course")
@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = { java.lang.Exception.class })
public class TpController {

	final static Logger logger = Logger.getLogger(TpController.class);
    
    /**
     * 
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "/public/partecipant/get")
    public ResponseEntity<Response> getByCourseName(
    		@RequestParam String courseName) {
    	return ResponseFactory.jsonOkResponse();
    }
    
    /**
     */
    @RequestMapping(method = RequestMethod.GET, path = "/employee/partecipant/get")
    public ResponseEntity<Response> getEByCourseName(
    		@RequestParam String courseName) {
    	return ResponseFactory.jsonOkResponse();
    }
    
    /**
     * 
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT, path = "/management/partecipant/put")
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { java.lang.Exception.class })
    public ResponseEntity<Response> addPartecipant(
    		@RequestBody AddPartecipantRequest request) {
    	return ResponseFactory.jsonOkResponse();
    }
    
    /**
     * 
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "/test")
    public ResponseEntity<Response> test() {
    	/** should nevere here... */
    	return ResponseFactory.jsonOkResponse();
    }
}
