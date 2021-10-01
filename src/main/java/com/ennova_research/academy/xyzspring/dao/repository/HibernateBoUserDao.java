package com.ennova_research.academy.xyzspring.dao.repository;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.ennova_research.academy.xyzspring.dao.model.BoUser;

/**
 * @author Alberto Ielpo
 */
@Service("hibernateBoUserDao")
public class HibernateBoUserDao implements BoUserDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public BoUser getByUsername(String username) {
		Assert.hasText(username, "username must have text");
		String queryStr = "select b from BoUser b where username = :username";
		var query = this.sessionFactory.getCurrentSession().createQuery(queryStr, BoUser.class);
		query.setParameter("username", username, org.hibernate.type.StringType.INSTANCE);
		var rs = query.getResultList();
		return CollectionUtils.isEmpty(rs) ? null : rs.get(0);
	}

}
