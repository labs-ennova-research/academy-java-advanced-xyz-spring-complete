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
@Table(name = "partecipant")
public class Partecipant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_partecipant")
	private Long idPartecipant;
	
	@Column(name = "id_course")
	private Long idCourse;
	
	@Column(name = "id_registered_user")
	private Long idRegisteredUser;

	public Long getIdPartecipant() {
		return idPartecipant;
	}

	public void setIdPartecipant(Long idPartecipant) {
		this.idPartecipant = idPartecipant;
	}

	public Long getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(Long idCourse) {
		this.idCourse = idCourse;
	}

	public Long getIdRegisteredUser() {
		return idRegisteredUser;
	}

	public void setIdRegisteredUser(Long idRegisteredUser) {
		this.idRegisteredUser = idRegisteredUser;
	}
	
}
