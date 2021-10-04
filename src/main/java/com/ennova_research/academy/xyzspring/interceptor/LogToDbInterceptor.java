package com.ennova_research.academy.xyzspring.interceptor;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ennova_research.academy.xyzspring.dao.model.Log;
import com.ennova_research.academy.xyzspring.dao.repository.LogDao;
import com.ennova_research.academy.xyzspring.utils.Json;

/**
 * @author Alberto Ielpo
 */
@Aspect
@Component
public class LogToDbInterceptor {
	
	final static Logger logger = Logger.getLogger(LogToDbInterceptor.class);
	
	@Autowired
	private LogDao logDao;
	
	@Around("@annotation(LogToDb)")
	public Object intercept(ProceedingJoinPoint joinPoint) throws Throwable {
	    try {
	    	String username = "anonymous";
	    	String path = "";
	    	
	    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    	if(auth instanceof UsernamePasswordAuthenticationToken) {
	    		username = ((UsernamePasswordAuthenticationToken) auth).getName();
	    	} 
	    	
	        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		    Method[] methods = joinPoint.getTarget().getClass().getMethods();
			for (Method m : methods) {
				if(m.getName().equals(signature.getMethod().getName())) {
					if (m.isAnnotationPresent(RequestMapping.class)) {
				    	RequestMapping ea = m.getAnnotation(RequestMapping.class);
				    	path = ea.path()[0];
				        logger.debug(String.format("Path called %s", path));				    	
				    }	
				}
			}
		    
		    Object[] signatureArgs = joinPoint.getArgs();
		    String args = Json.writeValuesAsString(signatureArgs);

		    logger.debug(String.format("Args: %s", args));
		    logger.debug(String.format("username %s", username));
		    
		    Log log = new Log();
		    log.setPath(path);
		    log.setUsername(username);
		    log.setDetails(args);
		    logDao.insert(log);
		    
	    } catch(Exception e) {
	    	logger.error(e);
	    } 
	    
	    return joinPoint.proceed();	    

	}
	

}
