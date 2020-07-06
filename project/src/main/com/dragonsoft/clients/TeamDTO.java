package main.com.dragonsoft.clients;

import java.util.List;

import main.com.dragonsoft.credentials.User;

public class TeamDTO {

	private Long id;

	private Department department;

	private List<User> users;
	
	private String head;
	
	private String deleteItem;

	// Getters and Setters

	public Long getId() {
		return id;
	}

	public String getDeleteItem() {
		return deleteItem;
	}

	public void setDeleteItem(String deleteItem) {
		this.deleteItem = deleteItem;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}
