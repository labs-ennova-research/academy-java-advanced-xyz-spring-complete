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
@Table(name = "log")
public class Log {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_log")
	private Long idBoUser;
	
	@Column(name = "path")    
	private String path;
	
	@Column(name = "username")    
	private String username;
	
	@Column(name = "details")    
	private String details;
	
	@Column(name = "server_timestamp")
	private long serverTimestamp;

	public Long getIdBoUser() {
		return idBoUser;
	}

	public long getServerTimestamp() {
		return serverTimestamp;
	}

	public void setServerTimestamp(long serverTimestamp) {
		this.serverTimestamp = serverTimestamp;
	}

	public void setIdBoUser(Long idBoUser) {
		this.idBoUser = idBoUser;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
}
