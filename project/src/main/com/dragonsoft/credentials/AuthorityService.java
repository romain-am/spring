package main.com.dragonsoft.credentials;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityService {
	@Autowired
	private AuthorityRepository authRepo;
	
	public List<Authority> listAll(){
		return (List<Authority>) authRepo.findAll();
	}

}
