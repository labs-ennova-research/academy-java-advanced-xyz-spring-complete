package com.ennova_research.academy.xyzspring.dao.model;
/**
 * @author Alberto Ielpo
 */
public enum RegisteredRoleType {
	STUDENT((short)0),
	TEACHER((short)1);
	
	private final short value;
	
	private RegisteredRoleType(short value) {
		this.value = value;
	}
	
	public short getValue() {
		return this.value;
	}
}
