package com.ennova_research.academy.xyzspring.dao.model;
/**
 * @author Alberto Ielpo
 */
public enum BoUserRoleType {
	
	ADMIN((short)0),
	READ_ALL((short)1),
	WRITE_ALL((short)2);
	
	private final short value;
	
	private BoUserRoleType(short value) {
		this.value = value;
	}
	
	public short getValue() {
		return this.value;
	}
	
}
