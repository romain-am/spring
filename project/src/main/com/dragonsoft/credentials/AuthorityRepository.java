package main.com.dragonsoft.credentials;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Long> {
	@Query(value = "SELECT a FROM Authority a WHERE a.name = :name ")
	public Authority search(@Param("name") AuthorityType name);
	
}
