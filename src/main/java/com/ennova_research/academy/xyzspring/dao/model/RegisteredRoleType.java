package com.ennova_research.academy.xyzspring.dao.model;
/**
 * @author Alberto Ielpo
 */
public enum RegisteredRoleType {
	
	STUDENT("STUDENT"),
	TEACHER("TEACHER");
	
	private final String value;
	
	private RegisteredRoleType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
	@Override
	public String toString() {
		return this.getValue();
	}
}
