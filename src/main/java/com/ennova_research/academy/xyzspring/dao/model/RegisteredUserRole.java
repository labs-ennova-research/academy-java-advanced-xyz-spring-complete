package com.ennova_research.academy.xyzspring.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Alberto Ielpo
 */
@Entity
@Table(name = "registered_user_role")
public class RegisteredUserRole {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_registered_user_role")
	private Long idRegisteredUserRole;
	
	@Column(name = "role_type")
	private Short roleType;

	public Long getIdRegisteredUserRole() {
		return idRegisteredUserRole;
	}

	public void setIdRegisteredUserRole(Long idRegisteredUserRole) {
		this.idRegisteredUserRole = idRegisteredUserRole;
	}

	public Short getRoleType() {
		return roleType;
	}

	public void setRoleType(Short roleType) {
		this.roleType = roleType;
	}
	
	
}
