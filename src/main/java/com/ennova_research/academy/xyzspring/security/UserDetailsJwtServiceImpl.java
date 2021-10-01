package com.ennova_research.academy.xyzspring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.ennova_research.academy.xyzspring.dao.model.BoUser;
import com.ennova_research.academy.xyzspring.dao.repository.BoUserDao;

@Service("userDetailsJwtServiceImpl")
@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = { java.lang.Exception.class })
public class UserDetailsJwtServiceImpl implements UserDetailsService {

	@Autowired
	private BoUserDao hibernateBoUserDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(StringUtils.hasText(username)) {
			BoUser boUser = hibernateBoUserDao.getByUsername(username);
			if(boUser != null) {
				return new UserDetailsImpl(boUser.getUsername(), boUser.getPassword(), boUser.getUserRole());
			}
		} 
		
		throw new UsernameNotFoundException(String.format("Invalid username %s", username));
					
	}

}
