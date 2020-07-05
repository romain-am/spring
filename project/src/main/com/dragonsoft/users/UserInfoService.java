package main.com.dragonsoft.users;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.com.dragonsoft.exceptions.NoEntryException;

@Service
public class UserInfoService {
	@Autowired
	private UserInfoRepository repo;
	
	public List<UserInfo> listAll(){
		return (List<UserInfo>) repo.findAll();
	}
	
	public void save(UserInfo UserInfo) {
		repo.save(UserInfo);
	}
	
	public UserInfo get(long id) throws NoEntryException {
		Optional<UserInfo> result = repo.findById(id);
		if (result.isPresent()) {
			return result.get();
		}
		else {
			throw new NoEntryException("No User Informations Found!");
		}
	}
	
	public void delete(long id) throws NoEntryException {
		Optional<UserInfo> result = repo.findById(id);
		if(result.isPresent()) {
			repo.deleteById(id);
		}
		else {
			throw new NoEntryException("User Informations already deleted!");
		}
	}
	
	public List<UserInfo> search(String keyword){
		return repo.search(keyword);
	}
	
}
