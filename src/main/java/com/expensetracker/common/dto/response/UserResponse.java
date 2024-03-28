package com.expensetracker.common.dto.response;

import java.util.List;

public class UserResponse {
	private Long id;
	private String userName;
	private String email;
	private boolean active;
	private List<RoleResponse> roles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<RoleResponse> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleResponse> roles) {
		this.roles = roles;
	}
}
