package main.com.dragonsoft.credentials;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import main.com.dragonsoft.exceptions.DuplicateEntryException;
import main.com.dragonsoft.exceptions.NoEntryException;
import main.com.dragonsoft.users.UserCV;
import main.com.dragonsoft.users.UserCVService;
import main.com.dragonsoft.users.UserInfo;
import main.com.dragonsoft.users.UserInfoService;

@Service
public class UserService {
	@Autowired
	private UserRepository repo;

	@Autowired
	private UserAuthorityRepository userAuthRepo;

	@Autowired
	private AuthorityRepository authRepo;

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private UserCVService userCVService;
	
	public User getLastIdinDb() {
		return repo.findTopByOrderByIdDesc();
	}
	
	public List<User> listAll(){
		return (List<User>) repo.findAll();
	}

	@Transactional(readOnly = true)
	public List<User> getAllCredentials(){
		List<User> userList = new ArrayList<>();
		try (Stream<User> userStream = repo.getAll()){

			userStream
			.sorted(Comparator.comparing(User::getUsername))
			.forEach(user -> {
				user.setUserCV(null);
				user.setUserInfo(null);
				userList.add(user);

			});
		};

		return userList;
	}

	@Transactional(readOnly = true)
	public List<User> getAllUsernames(){
		Gson gson = new GsonBuilder()
				.excludeFieldsWithoutExposeAnnotation().create();
		List<String> userListJson = new ArrayList<>();
		List<User> userList = new ArrayList<>();
		try (Stream<User> userStream = repo.getAll()){

			userStream
			.sorted(Comparator.comparing(User::getUsername))
			.forEach(user -> {
				user.setUserCV(null);
				user.setUserInfo(null);

				//CONVERT TO JSON
				String userJson = gson.toJson(user);	

				userListJson.add(userJson);

			});
		};

		//CONVERT JSON BACK TO USER
		userListJson.stream().forEach(user -> {
			User finalUser = gson.fromJson(user, new TypeToken<User>(){}.getType());
			userList.add(finalUser);
		});

		return userList;
	}

	public void save(User user) throws DuplicateEntryException {
		if(repo.search(user.getUsername()) != null) {
			throw new DuplicateEntryException("Entry already exist!");
		}
		else {

			user.setEnabled(true);
			user.setAccountNonExpired(true);
			user.setCredentialsNonExpired(true);
			user.setAccountNonLocked(true);

			repo.save(user);

			User result = repo.search(user.getUsername());

			//Set Authorities
			Authority authority = new Authority();
			authority.setId(2);
			authority.setName(AuthorityType.USER);

			UserAuthority userAuthority = new UserAuthority();
			userAuthority.setUserAuthority(result);
			userAuthority.setAuthority(authority);

			userAuthRepo.save(userAuthority);

			//Set default informations
			UserInfo info = new UserInfo();
			UserCV cv = new UserCV();

			info.setCus_addr(" ");

			//Current date
			Date in = new Date();
			LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
			Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());

			info.setCus_dob(out);
			info.setCus_email(result.getUsername());
			info.setCus_name(" ");
			info.setCus_tel(" ");
			info.setUserInfo(result);

			cv.setDatabasesList(" ");
			cv.setExperience(0);
			cv.setFrameworks(" ");
			cv.setLanguages(" ");
			cv.setMiddlewares(" ");
			cv.setOperating_system(" ");

			cv.setUserCV(result);

			userInfoService.save(info);
			userCVService.save(cv);

		}


	}

	public void addRole(String username, String role) {
		User result = repo.getUserByUsername(username);

		result.getUserAuthority().stream().forEach(uAuth -> {
			if(uAuth.getAuthority().getName().equals(AuthorityType.valueOf(role)))
				return;
		});

		Authority authority = authRepo.search(AuthorityType.valueOf(role));

		UserAuthority userAuthority = new UserAuthority();
		userAuthority.setUserAuthority(result);
		userAuthority.setAuthority(authority);

		userAuthRepo.save(userAuthority);

	}

	public void update (User user) {
		repo.save(user);
	}

	public User get(long id) throws NoEntryException {
		Optional<User> result = repo.findById(id);
		if(result.isPresent()) {
			return result.get();
		}
		else {
			throw new NoEntryException("No Entry!");
		}
	}

	public void delete(long id) throws NoEntryException {
		try{

			Set<UserAuthority> authorities = userAuthRepo.search(id);
			for(UserAuthority u : authorities) {
				userAuthRepo.deleteById(u.getId());
			}
			repo.deleteById(id);

		}catch(EmptyResultDataAccessException ex) {
			throw new NoEntryException("Entry already deleted!");
		}


	}

	public User search(String keyword){
		return repo.search(keyword);
	}

}
