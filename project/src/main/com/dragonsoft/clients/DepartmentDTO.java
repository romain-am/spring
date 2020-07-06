package main.com.dragonsoft.clients;

import java.util.List;

public class DepartmentDTO {
	
	private List<Team> teams;

	private Long id;

	private String name;

	private String description;

	private ClientInfo clientinfo;

	// Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ClientInfo getClientinfo() {
		return clientinfo;
	}

	public void setClientinfo(ClientInfo clientinfo) {
		this.clientinfo = clientinfo;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
}
