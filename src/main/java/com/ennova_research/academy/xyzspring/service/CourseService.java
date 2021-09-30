package com.ennova_research.academy.xyzspring.service;

import java.util.List;

import com.ennova_research.academy.xyzspring.dao.model.Course;

/**
 * @author Alberto Ielpo
 */
public interface CourseService {

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	List<Course> getAll() throws Exception;
	
	/**
	 * 
	 * @param courseName
	 * @return
	 * @throws Exception
	 */
	Course getByCourseName(String courseName) throws Exception;
	
	/**
	 * 
	 * @param courseId
	 * @return
	 * @throws Exception
	 */
	Course getById(long courseId) throws Exception;
	
	
}
