package com.ennova_research.academy.xyzspring.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ennova_research.academy.xyzspring.dao.model.RegisteredUser;
import com.ennova_research.academy.xyzspring.dto.factory.Response;
import com.ennova_research.academy.xyzspring.dto.factory.ResponseFactory;
import com.ennova_research.academy.xyzspring.service.RegisteredUserService;

/**
 * @author Alberto Ielpo
 */
@RestController
@RequestMapping(value = "${url.api}/course")
@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = { java.lang.Exception.class })
public class CourseController {

	@Autowired
	private RegisteredUserService registeredUserServiceImpl;

	final static Logger logger = Logger.getLogger(CourseController.class);

    /**
     * 
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "/get")
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
    
}