package main.com.dragonsoft.users;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo, Long> {
	
	
	@Query(value = "SELECT u FROM UserInfo u WHERE u.cus_name LIKE '%' || :keyword || '%'"
			+ " OR u.cus_email LIKE '%' || :keyword || '%'"
			+ " OR u.cus_addr LIKE '%' || :keyword || '%'")
	public List<UserInfo> search(@Param("keyword") String keyword);
}
