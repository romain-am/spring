package main.com.dragonsoft.credentials;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.content.commons.annotations.ContentId;
import org.springframework.content.commons.annotations.ContentLength;
import org.springframework.content.commons.annotations.MimeType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.google.gson.annotations.Expose;

import main.com.dragonsoft.clients.Team;
import main.com.dragonsoft.users.UserCV;
import main.com.dragonsoft.users.UserInfo;

@Entity
@JsonInclude(Include.NON_NULL)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "_class")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
property = "id")
public class User implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
    private Long id;

    @Column(unique = true, updatable = false)
    @Expose
    private String username;
    
    @Column(updatable = false)
    @JsonIgnore
    private String password;
    
    @Column
    @JsonIgnore
    private Date dateCreated;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userAuthority")
    private List<UserAuthority> userAuthority;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teams_id")
    @JsonIgnore
    private Team team;
    
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "userInfo", cascade=CascadeType.ALL)
    private UserInfo UserInfo;
    
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "userCV", cascade=CascadeType.ALL)
    private UserCV userCV;
    
    @Column
    private boolean enabled;
    
    @Column
    private boolean accountNonExpired;
    
    @Column
    private boolean credentialsNonExpired;
    
    @Column
    private boolean accountNonLocked;
    
    //User Picture (ContentId, ContentLength and mimeType)
    @ContentId
    @JsonIgnore
    private String contentId;

    @ContentLength
    @JsonIgnore
    private Long contentLength;

    // if you have rest endpoints
    @MimeType
    @JsonIgnore
    private String mimeType = "image/jpeg";

    public User() {

    }
    
    //Getters and Setters
    

	public UserCV getUserCV() {
		return userCV;
	}

	public List<UserAuthority> getUserAuthority() {
		return userAuthority;
	}

	public void setUserAuthority(List<UserAuthority> userAuthority) {
		this.userAuthority = userAuthority;
	}

	public void setContentLength(Long contentLength) {
		this.contentLength = contentLength;
	}

	public void setUserCV(UserCV userCV) {
		this.userCV = userCV;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public UserInfo getUserInfo() {
		return UserInfo;
	}

	public void setUserInfo(UserInfo UserInfo) {
		this.UserInfo = UserInfo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public long getContentLength() {
		return contentLength;
	}

	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	
	
    

}