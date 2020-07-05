package main.com.dragonsoft.clients;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.com.dragonsoft.exceptions.DepartmentNotFoundException;
import main.com.dragonsoft.exceptions.DuplicateEntryException;
import main.com.dragonsoft.exceptions.NoEntryException;
import main.com.dragonsoft.exceptions.TeamNotFoundException;

@Service
public class ClientInfoService {
	
	private static final Logger LOG
    = LogManager.getLogger();
	
	@Autowired
	private ClientInfoRepository clientInfoRepo;
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private DepartmentService departmentService;

	public List<ClientInfo> listAll(){
		return (List<ClientInfo>) clientInfoRepo.findAll();
	}
	
	//
	public void save(ClientInfo clientInfo) throws DuplicateEntryException {
		if(clientInfoRepo.search(clientInfo.getName()) != null) {
			throw new DuplicateEntryException("Entry already exist!");
		}
		else {
			clientInfoRepo.save(clientInfo);
		}


	}
	
	public ClientInfo getLastIdinDb() {
		return clientInfoRepo.findTopByOrderByIdDesc();
	}

	
	public ClientInfo get(long id) throws NoEntryException {
		Optional<ClientInfo> clientInfo = clientInfoRepo.findById(id);
		if (clientInfo.isPresent()) {
			return clientInfo.get();
		}
		else {
			throw new NoEntryException("Entry don't exist!");
		}
		
	}

	public void delete(ClientInfo clientInfo) throws NoEntryException, RuntimeException {
		if(clientInfoRepo.search(clientInfo.getName()) == null) {
			throw new NoEntryException("Entry already deleted!");
		}
		else {
			List<Department> departments = clientInfo.getDepartments();
			departments.stream().forEach(x -> {
				
				List<Team> teams = x.getTeams();
				teams.stream().forEach(y -> 
					{
						try {
							teamService.delete(y);
						} catch (TeamNotFoundException e1) {
							LOG.warn(e1.getMessage());
							throw new RuntimeException(e1.getMessage());
						}
					}
				); 
				
				try {
					departmentService.delete(x);
				} catch (DepartmentNotFoundException e) {
					LOG.warn(e.getMessage());
					throw new RuntimeException(e.getMessage());
				}
			});
			
			clientInfoRepo.delete(clientInfo);
		}


	}

	public ClientInfo search(String name){
		return clientInfoRepo.search(name);
	}

}
