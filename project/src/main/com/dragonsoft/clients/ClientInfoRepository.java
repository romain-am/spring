package main.com.dragonsoft.clients;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientInfoRepository extends CrudRepository<ClientInfo, Long> {
	@Query(value = "SELECT c FROM ClientInfo c WHERE c.name = :name ")
	public ClientInfo search(@Param("name") String name);
	
	public ClientInfo findTopByOrderByIdDesc();
	
}
