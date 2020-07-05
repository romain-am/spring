package main.com.dragonsoft.content;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import main.com.dragonsoft.credentials.User;
import main.com.dragonsoft.credentials.UserService;
import main.com.dragonsoft.exceptions.NoEntryException;
import main.com.dragonsoft.users.UserCV;

@Controller
public class UserContentStoreController {
	
	@Autowired
	private UserImageStore imageStore;
	
	@Autowired
	private UserPdfStore pdfStore;
	
	@Autowired
	private UserService userService; 
	
	@GetMapping(value="/accountImages/{id}")
	public @ResponseBody byte[] getProfilePicture(Map<String, Object> model, @PathVariable("id") Long id) throws IOException, NoEntryException {
		//Get the user
		User user = userService.get(id);
		InputStream profileImage = imageStore.getContent(user);
		
		return IOUtils.toByteArray(profileImage);
	}
	
	@PostMapping(value="/accountImages/{id}", consumes = {"multipart/form-data"})
	public String sendProfilePicture(Map<String, Object> model, @PathVariable("id") Long id, @RequestParam("uploadedFileName") 
	MultipartFile multipart) throws IOException, NoEntryException {
		//Get the user
		User user = userService.get(id);
		//Get the file
		InputStream fileStream = new ByteArrayInputStream(multipart.getBytes());
		//Store content
		imageStore.setContent(user, fileStream);
		
		return "redirect:/index_user";
	}
	
	@PostMapping(value="/accountPdf/{id}", consumes = {"multipart/form-data"})
	public String sendProfilePdf(Map<String, Object> model, @PathVariable("id") Long id, @RequestParam("uploadedFileName") 
	MultipartFile multipart) throws IOException, NoEntryException {
		//Get the user cv
		User user = userService.get(id);
		UserCV cv = user.getUserCV();
		
		//Get the file
		InputStream fileStream = new ByteArrayInputStream(multipart.getBytes());
		//Store content
		pdfStore.setContent(cv, fileStream);
		
		return "redirect:/index_user";
	}
	
	@GetMapping(value="/accountPdf/{id}")
	public @ResponseBody byte[] getPdf(Map<String, Object> model, @PathVariable("id") Long id) throws IOException, NoEntryException {
		//Get the user cv
		User user = userService.get(id);
		UserCV cv = user.getUserCV();
				
		InputStream pdf = pdfStore.getContent(cv);
		
		return IOUtils.toByteArray(pdf);
	}
}
