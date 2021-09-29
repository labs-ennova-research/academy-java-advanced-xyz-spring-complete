package com.ennova_research.academy.xyzspring.dao.model;
/**
 * @author Alberto Ielpo
 */
public enum BoUserRoleType {
	
	ADMIN("ADMIN"),
	READ_ALL("READ_ALL"),
	WRITE_ALL("WRITE_ALL");
	
	private final String value;
	
	private BoUserRoleType(String value) {
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
