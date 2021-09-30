package com.ennova_research.academy.xyzspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ennova_research.academy.xyzspring.dao.model.Partecipant;
import com.ennova_research.academy.xyzspring.dao.repository.PartecipantDao;

/**
 * @author Alberto Ielpo
 */
@Service("partecipantServiceImpl")
public class PartecipantServiceImpl implements PartecipantService {
	/**
	 * 
	 */
	@Autowired
	private PartecipantDao hibernatePartecipantDao;

	/**
	 * 
	 */
	@Override
	public void insert(long idRegisteredUser, long idCourse) throws Exception {
		var partecipant = new Partecipant();
		partecipant.setIdCourse(idCourse);
		partecipant.setIdRegisteredUser(idRegisteredUser);
		hibernatePartecipantDao.insert(partecipant);
	}
	
	/**
	 * 
	 */
	@Override
	public Partecipant get(long idRegisteredUser, long idCourse) throws Exception {
		return hibernatePartecipantDao.find(idRegisteredUser, idCourse);
	}

}
