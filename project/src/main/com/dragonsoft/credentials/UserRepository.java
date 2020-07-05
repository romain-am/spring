package main.com.dragonsoft.credentials;

import java.util.stream.Stream;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	@QueryHints(value = {
            @QueryHint(name = "HINT_FETCH_SIZE", value = "" + Integer.MIN_VALUE),
            @QueryHint(name = "HINT_CACHEABLE", value = "false")
    })
	
	User getUserByUsername(String username);
	
	@Query(value = "select u from User u")
	Stream<User> getAll();
	
	@Query(value = "SELECT u FROM User u WHERE u.username = :keyword ")
	public User search(@Param("keyword") String keyword);
	
	public User findTopByOrderByIdDesc();
}
