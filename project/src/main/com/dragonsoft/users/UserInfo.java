package main.com.dragonsoft.users;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.elasticsearch.annotations.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.google.gson.annotations.Expose;

import main.com.dragonsoft.credentials.User;

@Entity
@Table(name = "user_info")
@Document(indexName = "user_info_index", type = "user_info")
@JsonInclude(Include.NON_NULL)
@JsonTypeName("userInfo")
//Serializable so that we can store it in the session attributes
public class UserInfo implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Long id;
	
	@Expose
	private String cus_name;
	
	@Expose
    private Date cus_dob;
	
	@Expose
    private String cus_tel;
	
	@Expose
	private String cus_email;
	
	@Expose
	private String cus_addr;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cus_user_id")
    private User userInfo;
	
	public UserInfo() {
	}
	
	
	//Getters and Setters
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCus_name() {
		return cus_name;
	}

	public void setCus_name(String cus_name) {
		this.cus_name = cus_name;
	}

	public Date getCus_dob() {
		return cus_dob;
	}

	public void setCus_dob(Date cus_dob) {
		this.cus_dob = cus_dob;
	}

	public String getCus_tel() {
		return cus_tel;
	}

	public void setCus_tel(String cus_tel) {
		this.cus_tel = cus_tel;
	}

	public String getCus_email() {
		return cus_email;
	}

	public void setCus_email(String cus_email) {
		this.cus_email = cus_email;
	}

	public String getCus_addr() {
		return cus_addr;
	}

	public void setCus_addr(String cus_addr) {
		this.cus_addr = cus_addr;
	}

	public User getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(User userInfo) {
		this.userInfo = userInfo;
	}
	
	
}
