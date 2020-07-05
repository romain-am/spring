package main.com.dragonsoft.clients;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.com.dragonsoft.credentials.User;
import main.com.dragonsoft.credentials.UserRepository;
import main.com.dragonsoft.exceptions.DuplicateEntryException;
import main.com.dragonsoft.exceptions.TeamNotFoundException;

@Service
public class TeamService {

	@Autowired
	private TeamRepository teamRepo;

	@Autowired
	private UserRepository userRepo;


	public List<Team> listAll(){
		return (List<Team>) teamRepo.findAll();
	}

	public void deleteNonLinkedTeams() {
		teamRepo.deleteNonLinkedTeams();
	}

	@Transactional(readOnly = true)
	public List<Team> getAllNonLinkedTeams(){
		List<Team> teamList = teamRepo.findByDepartmentIsNull();
		teamList.stream().sorted(Comparator.comparing(Team::getHead)).collect(Collectors.toList());
		return teamList;
	}

	public void save(Team team) throws DuplicateEntryException {
		if(teamRepo.search(team.getHead()) != null && !team.getHead().equals("No teams left")) {
			throw new DuplicateEntryException("Entry already exist!");
		}
		else {

			teamRepo.save(team);
		}


	}
	
	public void update(Team team) {
		teamRepo.save(team);
	}

	public Team get(long id) throws TeamNotFoundException {
		Optional<Team> team = teamRepo.findById(id);
		if (team.isPresent()) {
		return team.get();
		}
		else {
			throw new TeamNotFoundException("Team not found!");
		}
	}

	public void delete(Team team) throws TeamNotFoundException {
		if(!team.getHead().equals("No teams left")) {
			if(teamRepo.search(team.getHead()) == null) {
				throw new TeamNotFoundException("Team "+team.getHead()+" already deleted!");
			}
			else {
				List<User> users = team.getUsers();
				users.stream().forEach((x) -> {
					x.setTeam(null);
					userRepo.save(x);
				});

				teamRepo.delete(team);
			}
		}
		else {
			List<User> users = team.getUsers();
			users.stream().forEach(x -> {
				x.setTeam(null);
				userRepo.save(x);
			});

			teamRepo.delete(team);
		}



	}

	public Team search(String name){
		return teamRepo.search(name);
	}

}
