package com.ennova_research.academy.xyzspring.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.ennova_research.academy.xyzspring.interceptor.LogToDb;
import com.ennova_research.academy.xyzspring.manager.CourseManager;

/**
 * @author Alberto Ielpo
 */
@RestController
@RequestMapping(value = "${url.api}/std/course")
@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = { java.lang.Exception.class })
public class StdCourseController {

	@Autowired
	private CourseManager courseManager;

	final static Logger logger = Logger.getLogger(StdCourseController.class);

    /**
     * 
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "/public/partecipant/get")
    public ResponseEntity<Response> getByCourseName(
    		@RequestParam String courseName) {
    	return courseManager.getCourseByName(courseName);
    }
    
    /**
     * 
     * @param courseName
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "/employee/partecipant/get")
    public ResponseEntity<Response> getEByCourseName(
    		@RequestParam String courseName) {
    	return courseManager.getECourseByName(courseName);
    }
    
    /**
     * 
     * @param request
     * @return
     */
    @LogToDb
    @RequestMapping(method = RequestMethod.PUT, path = "/management/partecipant/put")
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { java.lang.Exception.class })
    public ResponseEntity<Response> addPartecipant(
    		@RequestBody AddPartecipantRequest request) {
    	return courseManager.addCoursePartecipant(request);
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
    
    /**
     * 
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "/employee/advertisement/users/get")
    public ResponseEntity<Response> getRemoteUsers() {
    	return courseManager.getRemoteUsers();
    }
    
}