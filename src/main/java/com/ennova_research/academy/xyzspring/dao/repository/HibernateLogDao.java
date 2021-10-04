package com.ennova_research.academy.xyzspring.dao.repository;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ennova_research.academy.xyzspring.dao.model.Log;

/**
 * @author Alberto Ielpo
 */
@Service("hibernateLogDao")
public class HibernateLogDao implements LogDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void insert(Log log) {
		Assert.isTrue(log != null, "log must be valid");
		Assert.hasText(log.getPath(), "path must be valid");
		Assert.hasText(log.getUsername(), "path must be valid");
		if(log.getServerTimestamp() == 0) {
			log.setServerTimestamp(System.currentTimeMillis());
		}
		
		sessionFactory.getCurrentSession().save(log);
	}
	
}
