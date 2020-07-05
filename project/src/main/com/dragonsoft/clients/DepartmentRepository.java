package main.com.dragonsoft.clients;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {
	@Query(value = "SELECT d FROM Department d WHERE d.name = :name ")
	public Department search(@Param("name") String name);
	
	public Department findTopByOrderByIdDesc();
	
}
