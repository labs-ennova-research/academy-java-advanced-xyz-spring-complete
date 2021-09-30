package com.ennova_research.academy.xyzspring.dao.repository;

import com.ennova_research.academy.xyzspring.dao.model.Partecipant;

/**
 * @author Alberto Ielpo
 */
public interface PartecipantDao {

	public void insert(Partecipant partecipant);
	
	public Partecipant find(long idRegisteredUser, long idCourse);
	
}
