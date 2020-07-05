package main.com.dragonsoft.credentials;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@Table(name = "user_authority")
@JsonInclude(Include.NON_NULL)
@JsonTypeName("userAuthority")
public class UserAuthority implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User userAuthority;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "authority_id")
    private Authority authority;
	
	//Getters and Setters

	public Long getId() {
		return id;
	}

	public User getUserAuthority() {
		return userAuthority;
	}

	public void setUserAuthority(User userAuthority) {
		this.userAuthority = userAuthority;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}
	
	
}
