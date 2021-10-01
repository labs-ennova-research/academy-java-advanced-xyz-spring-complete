package com.ennova_research.academy.xyzspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ennova_research.academy.xyzspring.dao.model.BoUser;
import com.ennova_research.academy.xyzspring.dao.repository.BoUserDao;

/**
 * @author Alberto Ielpo
 */
@Service("boUserServiceImpl")
public class BoUserServiceImpl implements BoUserService {

	@Autowired
	private BoUserDao boUserDaoImpl;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/**
	 * @param username
	 * @param password
	 */
	public BoUser getByUsernameAndPassword(String username, String password) throws Exception {
		BoUser user = boUserDaoImpl.getByUsername(username);
		if(user == null) {
			return null;
		} else {
			if(passwordEncoder.matches(password, user.getPassword())) {
				return user;
			} else {
				return null;
			}
		}
	}
	
	
}
