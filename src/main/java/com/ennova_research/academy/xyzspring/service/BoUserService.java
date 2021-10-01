package com.ennova_research.academy.xyzspring.service;

import com.ennova_research.academy.xyzspring.dao.model.BoUser;

/**
 * @author Alberto Ielpo
 */
public interface BoUserService {

	BoUser getByUsernameAndPassword(String username, String password) throws Exception;
	
}
