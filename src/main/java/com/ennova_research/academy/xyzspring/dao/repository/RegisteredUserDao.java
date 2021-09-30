package com.ennova_research.academy.xyzspring.dao.repository;

import java.util.List;

import com.ennova_research.academy.xyzspring.dao.model.RegisteredUser;

/**
 * @author Alberto Ielpo
 */
public interface RegisteredUserDao  {
	
	public List<RegisteredUser> findAll();
	
	public List<RegisteredUser> findByCourseName(String courseName);

	public RegisteredUser findById(long registeredUserId);
}
