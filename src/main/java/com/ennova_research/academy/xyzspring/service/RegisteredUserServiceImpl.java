package com.ennova_research.academy.xyzspring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ennova_research.academy.xyzspring.dao.model.RegisteredUser;
import com.ennova_research.academy.xyzspring.dao.repository.RegisteredUserDao;

/**
 * @author Alberto Ielpo
 */
@Service("registeredUserServiceImpl")
public class RegisteredUserServiceImpl implements RegisteredUserService {

	/**
	 * 
	 */
	@Autowired
	private RegisteredUserDao hibernateRegisteredUserDao;
	
	/**
	 * 
	 */
	@Override
	public List<RegisteredUser> getAllUsers() throws Exception {		
		return this.hibernateRegisteredUserDao.findAll();
	}

	/**
	 * 
	 */
	@Override
	public List<RegisteredUser> getByCourseName(String courseName) throws Exception {
		return this.hibernateRegisteredUserDao.findByCourseName(courseName);
	}

}
