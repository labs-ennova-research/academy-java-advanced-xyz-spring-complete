package com.ennova_research.academy.xyzspring.dao.repository;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.ennova_research.academy.xyzspring.dao.model.Partecipant;

/**
 * @author Alberto Ielpo
 */
@Service("hibernatePartecipantDao")
public class HibernatePartecipantDao implements PartecipantDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void insert(Partecipant partecipant) {
		Assert.notNull(partecipant, "partecipant cannot be null");
		this.sessionFactory.getCurrentSession().save(partecipant);
	}

	@Override
	public Partecipant find(long idRegisteredUser, long idCourse) {
		Assert.isTrue(idRegisteredUser > 0, "idRegisteredUser must be valid");
		Assert.isTrue(idCourse > 0, "idCourse must be valid");
		
		String queryStr = "select p from Partecipant p where p.idCourse = :idCourse and p.idRegisteredUser = :idRegisteredUser";
		var query = this.sessionFactory.getCurrentSession().createQuery(queryStr, Partecipant.class);
		query.setParameter("idCourse", idCourse, org.hibernate.type.LongType.INSTANCE);
		query.setParameter("idRegisteredUser", idRegisteredUser, org.hibernate.type.LongType.INSTANCE);
		var res = query.getResultList();
		
		return CollectionUtils.isEmpty(res) ? null : res.get(0);
	}
	
}
