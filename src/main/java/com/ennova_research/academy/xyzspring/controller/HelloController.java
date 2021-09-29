package com.ennova_research.academy.xyzspring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ennova_research.academy.xyzspring.dao.model.RegisteredUser;
import com.ennova_research.academy.xyzspring.dto.factory.Response;
import com.ennova_research.academy.xyzspring.dto.factory.ResponseFactory;
import com.ennova_research.academy.xyzspring.service.RegisteredUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @author Alberto Ielpo
 */
@RestController
@RequestMapping(value = "${url.api}")
@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = { java.lang.Exception.class })
public class HelloController {

	@Autowired
	private RegisteredUserService registeredUserServiceImpl;
	
	/**
	 * 
	 * @return
	 */
    @RequestMapping(method = RequestMethod.GET, path = "/hello")
    public ResponseEntity<String> sayHello() {
        
    	final HttpHeaders httpHeaders= new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode user = mapper.createObjectNode();
		user.put("msg", "Hello world");		
        
        return new ResponseEntity<String>(user.toString(), httpHeaders, HttpStatus.OK);
    }
    
    /**
     * 
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "/get/all")
    public ResponseEntity<Response> getAll() {
        final HttpHeaders httpHeaders= new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        
        try {
        	var resModel = new ArrayList<com.ennova_research.academy.xyzspring.dto.model.RegisteredUser>();
        	        	
        	List<RegisteredUser> resDao = this.registeredUserServiceImpl.getAllUsers();        	
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
        	return ResponseFactory.jsonErrorResponse("Something bad happened");
        }
       
    }
    
}