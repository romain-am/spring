package main.com.dragonsoft.clients;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.com.dragonsoft.exceptions.DepartmentNotFoundException;
import main.com.dragonsoft.exceptions.DuplicateEntryException;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepo;

	@Autowired
	private TeamRepository teamRepo;

	public List<Department> listAll(){
		return (List<Department>) departmentRepo.findAll();
	}

	public void save(Department department) throws DuplicateEntryException {
		if(departmentRepo.search(department.getName()) != null) {
			throw new DuplicateEntryException("Entry already exist!");
		}
		else {
			departmentRepo.save(department);
		}


	}
	
	public Department getLastIdinDb() {
		return departmentRepo.findTopByOrderByIdDesc();
	}

	public void update(Department department) {
		departmentRepo.save(department);
	}

	public Department get(long id) throws DepartmentNotFoundException {
		Optional<Department> department = departmentRepo.findById(id);
		if (department.isPresent()) {
			return department.get();
		}
		else {
			throw new DepartmentNotFoundException("Department not found!");
		}
	}

	public void delete(Department department) throws DepartmentNotFoundException {
		if(departmentRepo.search(department.getName()) == null) {
			throw new DepartmentNotFoundException("Entry "+department.getName()+" already deleted!");
		}
		else {
			List<Team> teams = department.getTeams();
			if(teams != null) {
				teams.stream().forEach((x) -> {
					x.setDepartment(null);
					teamRepo.save(x);
				});
			}
			departmentRepo.delete(department);
		}

	}

	public Department search(String name){
		return departmentRepo.search(name);
	}

}
