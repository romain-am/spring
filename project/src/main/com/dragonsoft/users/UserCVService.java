package main.com.dragonsoft.users;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.com.dragonsoft.exceptions.NoEntryException;

@Service
public class UserCVService {
	
	@Autowired
	private UserCVRepository repo;
	
	public List<UserCV> listAll(){
		return (List<UserCV>) repo.findAll();
	}
	
	public void save(UserCV userCV) {
		repo.save(userCV);
	}
	
	public UserCV get(long id) throws NoEntryException {
		Optional<UserCV> result = repo.findById(id);
		if (result.isPresent()) {
			return result.get();
		}
		else {
			throw new NoEntryException("No CV Found!");
		}
	}
	
	public void delete(long id) throws NoEntryException {
		Optional<UserCV> result = repo.findById(id);
		if(result.isPresent()) {
			repo.deleteById(id);
		}
		else {
			throw new NoEntryException("CV already deleted!");
		}
	}

}
