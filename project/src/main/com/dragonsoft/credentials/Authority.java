
package main.com.dragonsoft.credentials;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@Table(name = "authority")
@JsonInclude(Include.NON_NULL)
@JsonTypeName("authority")
public class Authority {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
	@Column
    @Enumerated(EnumType.STRING)
    private AuthorityType name;
    
	@Transient
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "authority")
    private UserAuthority userAuthority;
	
    public Authority() {}

    public Authority(AuthorityType name) {
        this.name = name;
    }

 // getters and setters
    
	public AuthorityType getName() {
		return name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(AuthorityType name) {
		this.name = name;
	}

	public UserAuthority getUserAuthority() {
		return userAuthority;
	}

	public void setUserAuthority(UserAuthority userAuthority) {
		this.userAuthority = userAuthority;
	}
	
	

}
