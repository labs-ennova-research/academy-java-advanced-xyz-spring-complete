package com.ennova_research.academy.xyzspring.service;

import com.ennova_research.academy.xyzspring.dao.model.Partecipant;

/**
 * @author Alberto Ielpo
 */
public interface PartecipantService {

	void insert(long idRegisteredUser, long idCourse) throws Exception;
	
	Partecipant get(long idRegisteredUser, long idCourse) throws Exception;
	
}
