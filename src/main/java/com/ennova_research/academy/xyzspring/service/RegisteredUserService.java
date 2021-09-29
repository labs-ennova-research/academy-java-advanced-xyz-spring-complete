package com.ennova_research.academy.xyzspring.service;

import java.util.List;

import com.ennova_research.academy.xyzspring.dao.model.RegisteredUser;

/**
 * @author Alberto Ielpo
 */
public interface RegisteredUserService {

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	List<RegisteredUser> getAllUsers() throws Exception;
	
	/**
	 * 
	 * @param courseName
	 * @return
	 * @throws Exception
	 */
	List<RegisteredUser> getByCourseName(String courseName) throws Exception;
	
	
}
