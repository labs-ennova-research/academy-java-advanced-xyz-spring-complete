package com.ennova_research.academy.xyzspring.dao.repository;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.ennova_research.academy.xyzspring.dao.model.Course;

/**
 * @author Alberto Ielpo
 */
@Service("hibernateCourseDao")
public class HibernateCourseDao implements CourseDao  {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Course> findAll() {
		String queryStr = "select c from Course c";
		var query = this.sessionFactory.getCurrentSession().createQuery(queryStr, Course.class);
		return query.getResultList();
	}
	
	@Override
	public Course findByCourseName(String courseName) {
		Assert.notNull(courseName, "Course name must be valid");
		String queryStr = "select c from Course c where lower(c.name) = :courseName ";				
				
		var query = this.sessionFactory.getCurrentSession().createQuery(queryStr, Course.class);
		query.setParameter("courseName", courseName.toLowerCase(), org.hibernate.type.StringType.INSTANCE);
		var res = query.getResultList();
		return CollectionUtils.isEmpty(res) ? null : res.get(0);
	}
	
	@Override
	public Course findById(long courseId) {
		Assert.isTrue(courseId > 0, "courseId must be valid");
		String queryStr = "select c from Course c where idCourse = :courseId ";				
				
		var query = this.sessionFactory.getCurrentSession().createQuery(queryStr, Course.class);
		query.setParameter("courseId", courseId, org.hibernate.type.LongType.INSTANCE);
		var res = query.getResultList();
		return CollectionUtils.isEmpty(res) ? null : res.get(0);
	}



}
