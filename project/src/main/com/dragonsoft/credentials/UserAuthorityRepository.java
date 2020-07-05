package main.com.dragonsoft.credentials;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthorityRepository extends CrudRepository<UserAuthority, Long> {
	@Query(value = "SELECT u FROM UserAuthority u WHERE u.userAuthority.id = :id ")
	public Set<UserAuthority> search(@Param("id") Long id);
	
}
