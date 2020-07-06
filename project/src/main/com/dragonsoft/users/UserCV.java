package main.com.dragonsoft.users;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.content.commons.annotations.ContentId;
import org.springframework.content.commons.annotations.ContentLength;
import org.springframework.content.commons.annotations.MimeType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.google.gson.annotations.Expose;

import main.com.dragonsoft.credentials.User;

@Entity
@Table(name = "user_cv")
@JsonInclude(Include.NON_NULL)
@JsonTypeName("userCV")
public class UserCV {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	@Expose
	private Long id;

	@Column
	@Expose
	private String languages;

	@Column
	@Expose
	private String middlewares;

	@Column
	@Expose
	private String databasesList;

	@Column
	@Expose
	private String operating_system;

	@Column
	@Expose
	private Integer experience;

	@Column
	@Expose
	private String frameworks;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User userCV;

	@Transient
	@JsonIgnore
	private String language;

	@Transient
	@JsonIgnore
	private String middleware;

	@Transient
	@JsonIgnore
	private String adddatabase;

	@Transient
	@JsonIgnore
	private String os;

	@Transient
	@JsonIgnore
	private String framework;
	
	//User CV Pdf file (ContentId, ContentLength and mimeType)
    @ContentId
    @JsonIgnore
    private String contentId;

    @ContentLength
    @JsonIgnore
    private Long contentLength;

    // if you have rest endpoints
    @MimeType
    @JsonIgnore
    private String mimeType = "application/pdf";



	//Getters and Setters


	public Long getId() {
		return id;
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public Long getContentLength() {
		return contentLength;
	}

	public void setContentLength(Long contentLength) {
		this.contentLength = contentLength;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getDatabasesList() {
		return databasesList;
	}

	public void setDatabasesList(String databasesList) {
		this.databasesList = databasesList;
	}

	public String getLanguages() {
		return languages;
	}

	public String getMiddlewares() {
		return middlewares;
	}

	public String getOperating_system() {
		return operating_system;
	}

	public String getFrameworks() {
		return frameworks;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getMiddleware() {
		return middleware;
	}

	public void setMiddleware(String middleware) {
		this.middleware = middleware;
	}

	public String getAdddatabase() {
		return adddatabase;
	}

	public void setAdddatabase(String adddatabase) {
		this.adddatabase = adddatabase;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getFramework() {
		return framework;
	}

	public void setFramework(String framework) {
		this.framework = framework;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@JsonIgnore
	public List<String> getLanguagesArray() {
		return Arrays.asList(languages.split(","));
	}

	public void setLanguages(String languages) {
		this.languages = languages;
	}
	
	@JsonIgnore
	public void addLanguage() {
		if(!language.equals("")) {
			if(language.equals("empty")) {this.languages = "";}
			else{this.languages += ","+language;}
		}
		
	}
	
	@JsonIgnore
	public List<String> getMiddlewaresArray() {
		return Arrays.asList(middlewares.split(","));
	}

	public void setMiddlewares(String middlewares) {
		this.middlewares = middlewares;
	}
	
	@JsonIgnore
	public void addMiddleware() {
		if(!middleware.equals("")) {
			if(middleware.equals("empty")) {this.middlewares = "";}
			else{this.middlewares += ","+middleware;}
		}
		
	}
	
	@JsonIgnore
	public List<String> getDatabaseArray() {
		return Arrays.asList(databasesList.split(","));
	}
	
	@JsonIgnore
	public void addDatabase() {
		if(!adddatabase.equals("")) {
			if(adddatabase.equals("empty")) {this.databasesList = "";}
			else{this.databasesList += ","+adddatabase;}
		}
		
	}
	
	@JsonIgnore
	public List<String> getOperating_systemArray() {
		return Arrays.asList(operating_system.split(","));
	}

	public void setOperating_system(String operatring_system) {
		this.operating_system = operatring_system;
	}
	
	@JsonIgnore
	public void addOperating_system() {
		if(!os.equals("")) {
			if(os.equals("empty")) { this.operating_system = "";}
			else {this.operating_system += ","+os;}
		}
		
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}
	
	@JsonIgnore
	public List<String> getFrameworksArray() {
		return Arrays.asList(frameworks.split(","));
	}

	public void setFrameworks(String frameworks) {
		this.frameworks = frameworks;
	}
	
	@JsonIgnore
	public void addFramework() {
		if(!framework.equals("")) {
			if(framework.equals("empty")) {this.frameworks = "";}
			else {this.frameworks += ","+framework;}
		}
		
	}

	public User getUserCV() {
		return userCV;
	}

	public void setUserCV(User userCV) {
		this.userCV = userCV;
	}




}
