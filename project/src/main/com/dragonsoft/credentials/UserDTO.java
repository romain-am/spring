package main.com.dragonsoft.credentials;

import java.util.Date;
import java.util.List;

import main.com.dragonsoft.clients.Team;
import main.com.dragonsoft.users.UserCV;
import main.com.dragonsoft.users.UserInfo;

public class UserDTO {

    private Long id;

    
    private String username;
    
    private String password;
    
    private Date dateCreated;

    private List<UserAuthority> userAuthority;
    
    private Team team;
    
    private UserInfo UserInfo;
    
    private UserCV userCV;
    
    private boolean enabled;
    
    private boolean accountNonExpired;
    
    private boolean credentialsNonExpired;
    
    private boolean accountNonLocked;
    
    //User Picture (ContentId, ContentLength and mimeType)
    private String contentId;

    private Long contentLength;

    // if you have rest endpoints
    private String mimeType = "image/jpeg";
    
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
