package com.ennova_research.academy.xyzspring.dao.repository;

import com.ennova_research.academy.xyzspring.dao.model.BoUser;

/**
 * @author Alberto Ielpo
 */
public interface BoUserDao {

	public BoUser getByUsername(String username);
	
}
