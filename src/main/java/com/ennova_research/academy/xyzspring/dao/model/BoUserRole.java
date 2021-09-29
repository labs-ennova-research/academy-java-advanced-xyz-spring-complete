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
@Table(name = "bo_user_role")
public class BoUserRole {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_bo_user_role")
	private Long idBoUserRole;
	
	@Column(name = "role_type")
	private Short roleType;

	public Long getIdBoUserRole() {
		return idBoUserRole;
	}

	public void setIdBoUserRole(Long idBoUserRole) {
		this.idBoUserRole = idBoUserRole;
	}

	public Short getRoleType() {
		return roleType;
	}

	public void setRoleType(Short roleType) {
		this.roleType = roleType;
	}
	
}
