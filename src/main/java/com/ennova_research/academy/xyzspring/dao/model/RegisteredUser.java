package com.ennova_research.academy.xyzspring.dao.model;

import java.util.Date;

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
@Table(name = "registered_user")
public class RegisteredUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_registered_user")
	private Long idRegisteredUser;
	
	@Column(name = "name")    
	private String name;
	
	@Column(name = "surname")    
	private String surname;
	
	@Column(name = "fiscal_code")    
	private String fiscalCode;
	
	@Column(name = "sex")    
	private String sex;
	
	@Column(name = "born_date")    
	private Date bornDate;
		
	@Column(name = "id_registered_user_role")    
	private Long idRegisteredUserRole;

	public Long getIdRegisteredUser() {
		return idRegisteredUser;
	}

	public void setIdRegisteredUser(Long idRegisteredUser) {
		this.idRegisteredUser = idRegisteredUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getFiscalCode() {
		return fiscalCode;
	}

	public void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBornDate() {
		return bornDate;
	}

	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}

	public Long getIdRegisteredUserRole() {
		return idRegisteredUserRole;
	}

	public void setIdRegisteredUserRole(Long idRegisteredUserRole) {
		this.idRegisteredUserRole = idRegisteredUserRole;
	}

	
}
