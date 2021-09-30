package com.ennova_research.academy.xyzspring.dao.repository;

import java.util.List;

import com.ennova_research.academy.xyzspring.dao.model.Course;

/**
 * @author Alberto Ielpo
 */
public interface CourseDao  {
	
	public List<Course> findAll();
	
	public Course findByCourseName(String courseName);
	
	public Course findById(long courseId);
	
}
