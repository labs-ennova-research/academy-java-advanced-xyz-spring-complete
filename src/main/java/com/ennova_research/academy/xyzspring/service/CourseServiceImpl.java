package com.ennova_research.academy.xyzspring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ennova_research.academy.xyzspring.dao.model.Course;
import com.ennova_research.academy.xyzspring.dao.repository.CourseDao;

/**
 * @author Alberto Ielpo
 */
@Service("courseServiceImpl")
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDao hibernateCourseDao;
	
	@Override
	public List<Course> getAll() throws Exception {		
		return this.hibernateCourseDao.findAll();
	}

	@Override
	public Course getByCourseName(String courseName) throws Exception {
		return this.hibernateCourseDao.findByCourseName(courseName);
	}
	
	@Override
	public Course getById(long courseId) throws Exception {
		return this.hibernateCourseDao.findById(courseId);
	}

}
