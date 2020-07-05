package main.com.dragonsoft.clients;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.google.gson.annotations.Expose;

@Entity
@Table(name = "departments")
@JsonInclude(Include.NON_NULL)
@JsonTypeName("departments")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Department {
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "department", cascade=CascadeType.ALL)
	@Expose
	@Column
	@JsonManagedReference
	@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
	private List<Team> teams;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	@Expose
	private Long id;

	@Column
	@Expose
	private String name;

	@Column
	@Expose
	private String description;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "client_info_id", referencedColumnName="id", foreignKey = @ForeignKey(name = "client_info_id_fk"))
	@JsonBackReference
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
