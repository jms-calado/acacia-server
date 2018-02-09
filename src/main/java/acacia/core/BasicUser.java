package acacia.core;

import java.security.Principal;

public class BasicUser implements Principal {

	private String username;
	private String role;
	
	public BasicUser() {}
	public BasicUser(String username, String role) {
		this.username=username;
		this.role=role;		
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return username;
	}

}
