package com.ennova_research.academy.xyzspring.security;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ennova_research.academy.xyzspring.dao.model.BoUserRoleType;

/**
 * @author Alberto Ielpo
 */
public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = -5539631582913849144L;
	
	private String username;
	private String password;
	private SimpleGrantedAuthority role;
	
	public UserDetailsImpl(String username, String password, BoUserRoleType role) {
		this.username = username;
		this.password = password;
		this.role = new SimpleGrantedAuthority(role.getValue());
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(this.role);
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
