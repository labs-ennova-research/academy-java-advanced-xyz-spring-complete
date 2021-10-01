package com.ennova_research.academy.xyzspring.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.ennova_research.academy.xyzspring.dao.model.Course;
import com.ennova_research.academy.xyzspring.dao.model.Partecipant;
import com.ennova_research.academy.xyzspring.dao.model.RegisteredUser;
import com.ennova_research.academy.xyzspring.dto.factory.Response;
import com.ennova_research.academy.xyzspring.dto.factory.ResponseFactory;
import com.ennova_research.academy.xyzspring.dto.model.AddPartecipantRequest;
import com.ennova_research.academy.xyzspring.dto.model.CoursePartecipants;
import com.ennova_research.academy.xyzspring.service.CourseService;
import com.ennova_research.academy.xyzspring.service.PartecipantService;
import com.ennova_research.academy.xyzspring.service.RegisteredUserService;

/**
 * @author Alberto Ielpo
 */
@RestController
@RequestMapping(value = "${url.api}/std/course")
@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = { java.lang.Exception.class })
public class CourseController {

	@Autowired
	private RegisteredUserService registeredUserServiceImpl;
	
	@Autowired
	private PartecipantService partecipantServiceImpl;
	
	@Autowired
	private CourseService courseServiceImpl;

	final static Logger logger = Logger.getLogger(CourseController.class);

    /**
     * 
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "/public/partecipant/get")
    public ResponseEntity<Response> getByCourseName(
    		@RequestParam String courseName) {
        
        try {
        	var resModel = new ArrayList<com.ennova_research.academy.xyzspring.dto.model.RegisteredUser>();
        	List<RegisteredUser> resDao = this.registeredUserServiceImpl.getByCourseName(courseName);        	
    		if(resDao == null || resDao.size() == 0) {
    			return ResponseFactory.jsonOkResponse(resDao);
    		} else {
    			for(RegisteredUser r: resDao) {
    				var model = new com.ennova_research.academy.xyzspring.dto.model.RegisteredUser();
    	    		model.name = r.getName();
    	    		model.surname = r.getSurname();
    	    		resModel.add(model);
    			}
    		}

    		return ResponseFactory.jsonOkResponse(resModel);
        } catch (Exception e) {
        	logger.error(e);
        	return ResponseFactory.jsonErrorResponse("Something bad happened");
        }
    }
    
    /**
     * 
     * @param courseName
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "/employee/partecipant/get")
    public ResponseEntity<Response> getEByCourseName(
    		@RequestParam String courseName) {
        
        try {
        	var resModel = new CoursePartecipants();
        	
        	Course course = this.courseServiceImpl.getByCourseName(courseName);
        	if(course == null) {
        		return ResponseFactory.jsonInvalidDataResponse("Course not found");
        	}
        	
        	resModel.courseId = course.getIdCourse();
        	resModel.students = new ArrayList<com.ennova_research.academy.xyzspring.dto.model.RegisteredUser>();
        	
        	List<RegisteredUser> resDao = this.registeredUserServiceImpl.getByCourseName(courseName);        	
    		if(resDao == null || resDao.size() == 0) {
    			return ResponseFactory.jsonOkResponse(resDao);
    		} else {
    			for(RegisteredUser r: resDao) {
    				var model = new com.ennova_research.academy.xyzspring.dto.model.RegisteredUser();
    	    		model.id = r.getIdRegisteredUser();
    				model.name = r.getName();
    	    		model.surname = r.getSurname();
    	    		resModel.students.add(model);
    			}
    		}

    		return ResponseFactory.jsonOkResponse(resModel);
        } catch (Exception e) {
        	logger.error(e);
        	return ResponseFactory.jsonErrorResponse("Something bad happened");
        }
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
        
        try {

        	if(request == null || request.idCourse < 1 || request.idRegisteredUser < 1) {
        		return ResponseFactory.jsonInvalidDataResponse("Invalid parameters");
        	}
        	
        	Course course = courseServiceImpl.getById(request.idCourse);
        	if(course == null) {
        		return ResponseFactory.jsonInvalidDataResponse("Course does not exist");	
        	}
        	
        	RegisteredUser registeredUser = registeredUserServiceImpl.getById(request.idRegisteredUser);
        	if(registeredUser == null) {
        		return ResponseFactory.jsonInvalidDataResponse("Registered user does not exist");	
        	}
        	
        	Partecipant partecipant = partecipantServiceImpl.get(request.idRegisteredUser, request.idCourse);
        	if(partecipant != null) {
        		return ResponseFactory.jsonInvalidDataResponse("Student already inserted");	
        	}

        	partecipantServiceImpl.insert(request.idRegisteredUser, request.idCourse);
    		return ResponseFactory.jsonOkResponse();
        } catch (Exception e) {
        	logger.error(e);
        	return ResponseFactory.jsonErrorResponse("Something bad happened");
        }
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