package test.com.dragonsoft.servicelayer;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.Random;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import main.com.dragonsoft.clients.ClientInfo;
import main.com.dragonsoft.clients.ClientInfoService;
import main.com.dragonsoft.credentials.User;
import main.com.dragonsoft.credentials.UserService;
import main.com.dragonsoft.exceptions.DuplicateEntryException;
import main.com.dragonsoft.exceptions.NoEntryException;
import main.com.dragonsoft.utils.FileSystemOperations;
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
	private FileSystemOperations fsOperations;

	public UserServiceLayerTest(){
		Random rand = new Random();
		String randomNumber = String.valueOf(rand.nextInt(100000));
		this.numberTag = randomNumber;
		fsOperations = new FileSystemOperations();
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
		String fileSystemSeparator = fsOperations.getFileSystemSeparator("dont_escape");

		//Get last id from user repository
		User lastUser = userService.getLastIdinDb();
		Long lastUserId = lastUser.getId();
		
		//Get last id from clientInfo repository
		ClientInfo lastClientInfo = clientInfoService.getLastIdinDb();
		Long lastClientInfoId = lastClientInfo.getId();
		

		try {
			//Find dbpopulation folder
			File projectPath = fsOperations.getProjectPath();
			fsOperations.findDirfromRoot(projectPath, "resources");
			String resourceFolder = fsOperations.getDirectoryFound();
			File dbpopulationFolder = new File(resourceFolder + fileSystemSeparator + DBPOPULATIONFOLDER); 
			
			JsonPopulator jsonCreator = new JsonPopulator();
			//List files to parse
			String[] populatorFiles = dbpopulationFolder.list();
			for(int i = 0; i < populatorFiles.length; i++) {
				String populatorFile = dbpopulationFolder.getAbsolutePath() + fileSystemSeparator +populatorFiles[i];
				jsonCreator.createUsers(populatorFile, lastUserId);
				jsonCreator.createClientInfos(populatorFile, lastClientInfoId);
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
