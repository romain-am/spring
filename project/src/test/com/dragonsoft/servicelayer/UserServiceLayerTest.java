package test.com.dragonsoft.servicelayer;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.Optional;
import java.util.Random;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import main.com.dragonsoft.clients.ClientInfo;
import main.com.dragonsoft.clients.ClientInfoService;
import main.com.dragonsoft.clients.Department;
import main.com.dragonsoft.clients.DepartmentService;
import main.com.dragonsoft.credentials.User;
import main.com.dragonsoft.credentials.UserService;
import main.com.dragonsoft.exceptions.DuplicateEntryException;
import main.com.dragonsoft.exceptions.NoEntryException;
import main.com.dragonsoft.utils.CustomFileUtil;
import test.com.dragonsoft.servicelayer.contextconfigs.UserServiceTestJpaConfig;

@Transactional
@SpringJUnitConfig({UserServiceTestJpaConfig.class})
public class UserServiceLayerTest {

	/*
	 * Careful this test class will create JSON files 
	 * to populate your DATABASE the next time you boot up SPRING !
	 * generated JSON files are in resources/db_population_json
	 * 
	 * If you don't want it, don't run populationTest() .
	 * 
	 * For testing only !
	 */

	@Autowired
	private UserService userService;
	@Autowired
	private ClientInfoService clientInfoService;
	private static String numberTag;
	private static final String USERNAME = "SpringTest"+numberTag+"@Test.fr";
	private static final String PASSWORD = "SpringTestPassword";
	private static final String DBPOPULATIONFOLDER = "db_population";

	public UserServiceLayerTest(){
		Random rand = new Random();
		String randomNumber = String.valueOf(rand.nextInt(100000));
		this.numberTag = randomNumber;
	}

	public User createTestUser() {
		User testUser = new User();
		testUser.setUsername(USERNAME);
		testUser.setPassword(PASSWORD);

		return testUser;
	}

	@Test
	public void populationTest() {
		// get FileSystem separator
		FileSystem fileSystem = FileSystems.getDefault();
		String fileSystemSeparator = fileSystem.getSeparator();

		//Instantiate the json file creator
		JsonPopulator jsonCreator = new JsonPopulator();

		//Get last id from user repository
		User lastUser = userService.getLastIdinDb();
		Long userId = lastUser.getId();
		
		//Get last id from clientInfo repository
		ClientInfo lastClientInfo = clientInfoService.getLastIdinDb();
		Long clientInfoId = lastClientInfo.getId();
		

		try {
			//Find resources folder
			Resource resource = new ClassPathResource("/resources/" + DBPOPULATIONFOLDER);
			File dbpopulationPath = resource.getFile();
			String projectPath = dbpopulationPath.getAbsolutePath().split("target")[0];
			CustomFileUtil fileUtil = new CustomFileUtil();
			fileUtil.findDir(new File(projectPath), "resources");
			String rsrcFolder = fileUtil.getResults();
			
			//List files to parse
			String[] populatorFiles = dbpopulationPath.list();
			for(int i = 0; i < populatorFiles.length; i++) {
				jsonCreator.createUsers(rsrcFolder + fileSystemSeparator +DBPOPULATIONFOLDER+ fileSystemSeparator +populatorFiles[i], userId);
				jsonCreator.createClientInfos(rsrcFolder + fileSystemSeparator +DBPOPULATIONFOLDER+ fileSystemSeparator +populatorFiles[i], clientInfoId);
			}
		} catch (IOException e1) {
			fail("Could not create json file for users database population :");
			fail(e1.getMessage());
		} 
	}

	@Test
	public void saveUser() {
		try {
			userService.save(createTestUser());
		} catch (DuplicateEntryException e) {
			fail("Seems like test entry wasn't removed from last test run :");
			fail(e.getMessage());
		}

		User toDelete = userService.search(USERNAME);
		if(!Optional.ofNullable(toDelete).isPresent()) {
			fail("The test user wasn't persisted in database");
		}
	}

	@Test
	public void duplicateUser() {
		try {
			userService.save(createTestUser());
		} catch (DuplicateEntryException e) {
			fail("Seems like test entry wasn't removed from last test run :");
			fail(e.getMessage());
		}
		try {
			userService.save(createTestUser());
		} catch (DuplicateEntryException e) {
			Assert.assertEquals(e.getMessage(),"Entry already exist!");
		}

	}

	@Test
	public void deleteUser() {
		try {
			userService.save(createTestUser());
		} catch (DuplicateEntryException e) {
			fail("Seems like test entry wasn't removed from last test run :");
			fail(e.getMessage());
		}

		User toDelete = userService.search(USERNAME);
		if(Optional.ofNullable(toDelete).isPresent()) {
			try {
				userService.delete(toDelete.getId());
			} catch (NoEntryException e) {
				fail("There was an error trying to delete the user from the database :");
				fail(e.getMessage());
			}
		}
		else {
			fail("The test user wasn't persisted in database");
		}
	}

}
