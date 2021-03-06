package com.ennova_research.academy.xyzspring.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Alberto Ielpo
 */
@Entity
@Table(name = "bo_user")
public class BoUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_bo_user")
	private Long idBoUser;
	
	@Column(name = "full_name")    
	private String fullName;
	
	@Column(name = "username")    
	private String username;
	
	@Column(name = "password")    
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "user_role")    
	private BoUserRoleType userRole;

	public Long getIdBoUser() {
		return idBoUser;
	}

	public void setIdBoUser(Long idBoUser) {
		this.idBoUser = idBoUser;
	}

	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public BoUserRoleType getUserRole() {
		return userRole;
	}

	public void setUserRole(BoUserRoleType userRole) {
		this.userRole = userRole;
	}

	
}
