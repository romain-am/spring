package main.com.dragonsoft.clients;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PreDestroy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import main.com.dragonsoft.credentials.User;
import main.com.dragonsoft.credentials.UserDTO;
import main.com.dragonsoft.credentials.UserService;
import main.com.dragonsoft.elasticsearch.ElasticsearchUserInfoService;
import main.com.dragonsoft.enums.UrlConstants;
import main.com.dragonsoft.exceptions.DepartmentNotFoundException;
import main.com.dragonsoft.exceptions.DuplicateEntryException;
import main.com.dragonsoft.exceptions.NoEntryException;
import main.com.dragonsoft.exceptions.TeamNotFoundException;
import main.com.dragonsoft.users.UserInfo;

@Controller
@SessionAttributes("userInfoList")
public class ClientsController {

	private static final Logger LOG
	= LogManager.getLogger();
	
	@Autowired
    private RestHighLevelClient elasticsearchClient;

	@Autowired
	private ClientInfoService clientInfoService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private TeamService teamService;

	@Autowired
	private UserService userService;

	@Autowired
	private ElasticsearchUserInfoService elasticUserInfoService;

	private static final String NOTEAMS = "No teams left";

	//Initialize session attributes
	@ModelAttribute("userInfoList")
	public List<UserInfo> initUserInfoList() {
		return new ArrayList<>();
	}

	//HOME PAGE
	@GetMapping(value="/index_clients")
	public String choseRole(@ModelAttribute("userInfoList") List<UserInfo> userInfoList, Map<String, Object> model, @RequestParam(required = false) Long d, SessionStatus status) throws DepartmentNotFoundException {

		//NEW INFORMATIONS
		model.put("newClient", new ClientInfoDTO());
		model.put("newDepartment", new DepartmentDTO());
		model.put("newTeam", new TeamDTO());
		model.put("deleteTeam", new TeamDTO());
		model.put("linkUser", new UserDTO());
		model.put("linkTeam", new TeamDTO());

		//GET NON LINKED TEAMS (NO DEPARTMENT)
		List<Team> nonLinkedTeams = teamService.getAllNonLinkedTeams();
		model.put("nonLinkedTeams", nonLinkedTeams);

		List<ClientInfo> clientInfoList = clientInfoService.listAll();
		List<User> userList = userService.getAllUsernames();

		model.put("clientInfoList", clientInfoList);
		model.put("userList", userList);

		if(d != null) {
			Department department = departmentService.get(d);
			List<Team> teams = department.getTeams();

			model.put("department", department);
			model.put("teamsList", teams);
		}
		else {
			Department department = new Department();
			List<Team> teams = new ArrayList<>();

			model.put("department", department);
			model.put("teamsList", teams);
		}

		//CLEANUP SESSION ATTRIBUTES
		status.setComplete();

		return UrlConstants.INDEX_CLIENTS.toString().toLowerCase();
	}

	//CREATE NEW CLIENT
	@PostMapping(value = "/add_client")
	public String newClientInfoForm(@ModelAttribute("newClient") ClientInfoDTO clientInfo) throws DuplicateEntryException {
		ClientInfo clientInfoDB = new ClientInfo();
		clientInfoDB.setName(clientInfo.getName());
		clientInfoDB.setTelephone(clientInfo.getTelephone());
		clientInfoDB.setLocation(clientInfo.getLocation());
		clientInfoDB.setDepartments(clientInfo.getDepartments());
		clientInfoService.save(clientInfoDB);
		return "redirect:/"+UrlConstants.INDEX_CLIENTS.toString().toLowerCase();
	}

	//CREATE NEW DEPARTMENT
	@PostMapping(value = "/add_department")
	public String newDepartmentForm(@ModelAttribute("newDepartment") DepartmentDTO department) throws DuplicateEntryException {
		Department departmentDB = new Department();
		departmentDB.setName(department.getName());
		departmentDB.setDescription(department.getDescription());
		departmentDB.setClientinfo(department.getClientinfo());
		departmentDB.setTeams(department.getTeams());
		departmentService.save(departmentDB);
		return "redirect:/index_clients";
	}

	//CREATE TEAM
	@PostMapping(value = "/add_team")
	public String newTeamForm(@ModelAttribute("newTeam") TeamDTO team) throws DuplicateEntryException {
		Team teamDB = new Team();
		teamDB.setHead(team.getHead());
		teamDB.setDepartment(team.getDepartment());
		teamService.save(teamDB);
		return "redirect:/"+UrlConstants.INDEX_CLIENTS.toString().toLowerCase();
	}

	//DELETE
	@PostMapping(value = "/delete_team")
	public String deleteTeamForm(@ModelAttribute("deleteTeam") TeamDTO team) throws NoEntryException, DuplicateEntryException, TeamNotFoundException, DepartmentNotFoundException {
		Team selectedTeam = teamService.get(team.getId());

		switch (team.getDeleteItem()) {
		case "Team":
			Long departmentId = selectedTeam.getDepartment().getId();
			teamService.delete(selectedTeam);
			Department checkDepartment = departmentService.get(departmentId);
			if(checkDepartment.getTeams().isEmpty()) {  
				Team noTeamsafterDelete = new Team();
				noTeamsafterDelete.setDepartment(checkDepartment);
				noTeamsafterDelete.setHead(NOTEAMS);

				teamService.save(noTeamsafterDelete);
			}
			break;
		case "Department":
			Department selectedDepartment = selectedTeam.getDepartment();
			departmentService.delete(selectedDepartment);
			break;
		case "Client":
			ClientInfo selectedClient = selectedTeam.getDepartment().getClientinfo();
			clientInfoService.delete(selectedClient);
			break;
		default : 
		}

		return "redirect:/"+UrlConstants.INDEX_CLIENTS.toString().toLowerCase();
	}

	//LINK A USER
	@PostMapping(value = "/link")
	public String newUserLink(@ModelAttribute("linkUser") UserDTO user) throws NoEntryException, TeamNotFoundException {
		User selectedUser = userService.get(user.getId());
		Team selectedTeam = teamService.get(user.getTeam().getId());

		if(!selectedTeam.getHead().equals(NOTEAMS)) {
			selectedUser.setTeam(selectedTeam);

			userService.update(selectedUser);
			return "redirect:/"+UrlConstants.INDEX_CLIENTS.toString().toLowerCase();
		}
		else {
			return "redirect:/"+UrlConstants.INDEX_CLIENTS.toString().toLowerCase();
		}
	}

	//LINK A TEAM
	@PostMapping(value = "/link_team")
	public String newTeamLink(@ModelAttribute("linkTeam") TeamDTO team) throws TeamNotFoundException, DepartmentNotFoundException, RuntimeException {
		Team selectedTeam = teamService.get(team.getId());
		Department selectedDepartment = departmentService.get(team.getDepartment().getId());

		//Delete "No teams left" sign if exists
		selectedDepartment.getTeams().stream().forEach(teamItem ->{
			if(teamItem.getHead().equals(NOTEAMS)) {
				try {
					teamService.delete(teamItem);
				} catch (TeamNotFoundException e) {
					LOG.warn(e.getMessage());
					throw new RuntimeException(e.getMessage());
				}
			}
		});

		selectedTeam.setDepartment(selectedDepartment);

		teamService.update(selectedTeam);

		return "redirect:/"+UrlConstants.INDEX_CLIENTS.toString().toLowerCase();
	}

	//SEARCH USERS BY SKILLS
	@PostMapping(value="/search_cvs")
	public String searchCvs(ModelMap model, @RequestParam(required = true) String keywords) {
		List<UserInfo> userInfoList = elasticUserInfoService.findUsersWithCvs(keywords);

		//RETURN LIST OF USERS
		model.addAttribute("userInfoList", userInfoList);
		return "redirect:/"+UrlConstants.INDEX_CLIENTS.toString().toLowerCase();
	}
	
	//CLOSE THE ELASTIC SEARCH REST CLIENT WHEN CONTAINER GOES DOWN
	@PreDestroy
    public void cleanup() {
        try {
            LOG.info("Closing the ES REST client");
            this.elasticsearchClient.close();
        } catch (IOException ioe) {
            LOG.error("Problem occurred when closing the ES REST client", ioe);
        }
    }
}
