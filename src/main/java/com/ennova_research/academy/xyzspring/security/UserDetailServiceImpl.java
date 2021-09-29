package com.ennova_research.academy.xyzspring.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//Optional<User> user = userRepository.findByUserName(userName);
//		user.orElseThrow(() -> new UsernameNotFoundException(userName + " not found."));
//		return user.map(UserDetailsImpl::new).get();
		//return new UserDetailsImpl();
		return null;
	}

}
