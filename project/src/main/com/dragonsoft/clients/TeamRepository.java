package main.com.dragonsoft.clients;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {
	@Query(value = "SELECT t FROM Team t WHERE t.head = :name ")
	public Team search(@Param("name") String name);

	@Transactional
	@Procedure(procedureName = "DeleteAllNonLinkedTeams")
	public void deleteNonLinkedTeams();
	
	List<Team> findByDepartmentIsNull();
	

}
