package com.ennova_research.academy.xyzspring.dao.repository;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ennova_research.academy.xyzspring.dao.model.RegisteredRoleType;
import com.ennova_research.academy.xyzspring.dao.model.RegisteredUser;

/**
 * @author Alberto Ielpo
 */
@Service("hibernateRegisteredUserDao")
public class HibernateRegisteredUserDao implements RegisteredUserDao  {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<RegisteredUser> findAll() {
		String queryStr = "select r from RegisteredUser r";
		var query = this.sessionFactory.getCurrentSession().createQuery(queryStr, RegisteredUser.class);
		return query.getResultList();
	}
	
	@Override
	public List<RegisteredUser> findByCourseName(String courseName) {
		Assert.notNull(courseName, "Course name must be valid");
		
		String queryStr = 
			"select r from RegisteredUser r "
			+ "join Partecipant p on r.idRegisteredUser = p.idRegisteredUser "
			+ "join Course c on c.idCourse = p.idCourse "
			+ "where lower(c.name) = :courseName "
				+ "and r.userRole = :roleType";
				
		var query = this.sessionFactory.getCurrentSession().createQuery(queryStr, RegisteredUser.class);
		query.setParameter("courseName", courseName.toLowerCase(), org.hibernate.type.StringType.INSTANCE);
		query.setParameter("roleType", RegisteredRoleType.STUDENT.getValue(), org.hibernate.type.StringType.INSTANCE);
		return query.getResultList();
	}



}
