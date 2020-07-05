package main.com.dragonsoft.users;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCVRepository extends CrudRepository<UserCV,Long> {

}
