package com.infopulse.task.app.client.dataentities;

/**
 * A simple data type that represents the user.
 */
public class User {

	private final String id;
	private final String name;
	private final String surname;
	private final String email;
	private Role role;
	
	public User(String id, String name, String surname, String email, String role){
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.role = Role.valueOf(role);
	}
	
	public String getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public String getSurname(){
		return surname;
	}
	
	public String getEmail(){
		return email;
	}
	
	public String getRole(){
		return role.toString();
	}
	
	public void setRole(String role) {
		this.role = Role.valueOf(role);
	}
	
}
