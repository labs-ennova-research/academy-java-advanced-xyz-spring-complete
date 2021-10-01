package com.ennova_research.academy.xyzspring.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ennova_research.academy.xyzspring.dao.model.BoUser;
import com.ennova_research.academy.xyzspring.dto.factory.Response;
import com.ennova_research.academy.xyzspring.dto.factory.ResponseFactory;
import com.ennova_research.academy.xyzspring.dto.model.LoginRequest;
import com.ennova_research.academy.xyzspring.security.JwtTokenManager;
import com.ennova_research.academy.xyzspring.service.BoUserService;

/**
 * @author Alberto Ielpo
 */
@RestController
@RequestMapping(value = "${url.api}/tp/login")
@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = { java.lang.Exception.class })
public class TpLoginController {

	final static Logger logger = Logger.getLogger(TpLoginController.class);

	@Autowired
	private BoUserService boUserServiceImpl;
	
	@Autowired
	private JwtTokenManager jwtTokenManager;
	
    /**
     * 
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, path = "/public/do")
    public ResponseEntity<Response> doLogin(@RequestBody LoginRequest request) {
    	try {
    		
    		if(request == null || !StringUtils.hasText(request.username)) {
    			return ResponseFactory.jsonInvalidDataResponse("Invalid parameters");
    		}
    		
    		BoUser boUser = boUserServiceImpl.getByUsernameAndPassword(request.username, request.password);
    		if(boUser == null) {
    			return ResponseFactory.jsonForbiddenDataResponse("Forbidden");	
    		}
    		
    		/* create token */
    		String token = jwtTokenManager.createToken(request.username);
    		jwtTokenManager.isValidToken(token);
    		
    		return ResponseFactory.jsonOkResponse(token);
    	} catch(Exception e) {
    		logger.error("Something goes bad...", e);
    		return ResponseFactory.jsonForbiddenDataResponse("Forbidden");
    	}
    }
    
}
