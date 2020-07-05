package main.com.dragonsoft.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import main.com.dragonsoft.credentials.Authority;
import main.com.dragonsoft.credentials.AuthorityService;
import main.com.dragonsoft.credentials.AuthorityType;
import main.com.dragonsoft.credentials.User;
import main.com.dragonsoft.credentials.UserService;
import main.com.dragonsoft.enums.UrlConstants;
import main.com.dragonsoft.exceptions.DuplicateEntryException;
import main.com.dragonsoft.exceptions.EmailFormatException;
import main.com.dragonsoft.exceptions.NoEntryException;
import main.com.dragonsoft.utils.EmailValidator;

@Controller
public class UserController {
	@Autowired
	private ApplicationContext context;

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthorityService authService;

	//LOGIN PAGE
	@GetMapping(value="/login")
	public String login(Map<String, Object> model) {
		model.put("user", new User());
		return UrlConstants.LOGIN.toString().toLowerCase();
	}

	//CHOOSE ROLE PAGE
	@GetMapping(value="/login_role")
	public String choseRole(Map<String, Object> model) {
		//CURRENTLY LOGGED USER
		String loggedUser = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		
		User user = userService.search(loggedUser);
		model.put("user", user);

		return UrlConstants.LOGIN_ROLE.toString().toLowerCase();
	}

	//KEEP ROLE
	@PostMapping(value="/keeprole", params = {"role"})
	public String keepRole(@RequestParam(value="role") String role) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
		for(int i = 0; i < updatedAuthorities.size(); i++) {
			String pass = "ROLE_"+role;
			if(!updatedAuthorities.get(i).getAuthority().equals(pass)) {
				updatedAuthorities.remove(i);
				i--;
			}
		}
		
		Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);
		SecurityContextHolder.getContext().setAuthentication(newAuth);
		
		return "redirect:/index_"+role.toLowerCase();
	}

	//NEW USERINFO
	@GetMapping("/new")
	public String newUserInfoForm(Map<String, Object> model) {
		model.put("UserInfo", new UserInfo());
		return "new_userinfo";
	}
	
	//NEW CV INFO
	@PostMapping(value = "/add_cv_info")
	public String newCVInfoForm(@ModelAttribute("user") User form) throws NoEntryException {
		User user = userService.get(form.getId());
		
		user.getUserCV().setAdddatabase(form.getUserCV().getAdddatabase());
		user.getUserCV().setFramework(form.getUserCV().getFramework());
		user.getUserCV().setLanguage(form.getUserCV().getLanguage());
		user.getUserCV().setMiddleware(form.getUserCV().getMiddleware());
		user.getUserCV().setOs(form.getUserCV().getOs());
		
		user.getUserCV().addLanguage();
		user.getUserCV().addDatabase();
		user.getUserCV().addMiddleware();
		user.getUserCV().addFramework();
		user.getUserCV().addOperating_system();
		user.getUserCV().setUserCV(user);
		
		userService.update(user);
		return "redirect:/"+UrlConstants.INDEX_USER.toString().toLowerCase();
	}

	//UPDATE USER
	@PostMapping(value = "/save")
	public String updateUser(@ModelAttribute("user") User user) throws DuplicateEntryException {
		userService.update(user);
		return "redirect:/"+UrlConstants.INDEX_ADMIN.toString().toLowerCase();
	}

	//REGISTER
	@PostMapping(value = "/register")
	public String registerUser(@ModelAttribute("user") User user) throws DuplicateEntryException, EmailFormatException {
		//EMAIL FORMAT CHECK
		EmailValidator validator = new EmailValidator();
		if(!validator.validate(user.getUsername())) {
			throw new EmailFormatException("Not a valid email adress !");
		}

		BCryptPasswordEncoder encoder = (BCryptPasswordEncoder) context.getBean("passwordEncoder");	
		String encodedPass = encoder.encode(user.getPassword());
		user.setPassword(encodedPass);
		userService.save(user);

		return "redirect:/";
	}

	//ADD ROLE
	@PostMapping(value="/addrole", params = {"id", "role"})
	public String addRole(@RequestParam(value="id") String username, @RequestParam(value="role") String role) {
		userService.addRole(username, role);
		return "redirect:/"+UrlConstants.INDEX_ADMIN.toString().toLowerCase();
	}

	//ADMIN HOME
	@GetMapping("/index_admin")
	public ModelAndView adminHome(@RequestParam(required = false) Long id) throws NoEntryException {
		List<User> listUsers = userService.getAllCredentials();
		ModelAndView mav = new ModelAndView("index_admin");
		mav.addObject("listUsers", listUsers);

		List<Authority> allAuthorities = authService.listAll();
		mav.addObject("listAuthorities", allAuthorities);

		mav.addObject("authTypes", AuthorityType.values());

		if(id != null) {
			User user = userService.get(id);
			mav.addObject("user", user);
		}
		else {
			User user = new User();
			mav.addObject("user", user);
		}

		return mav;
	}

	//USER HOME
	@GetMapping("/index_user")
	public ModelAndView userHome() {
		//CURRENTLY LOGGED USER
		String loggedUser = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		
		ModelAndView mav = new ModelAndView("index_user");

		User user = userService.search(loggedUser);
		mav.addObject("user", user);

		return mav;
	}

	//DELETE USER
	@GetMapping(value = "/delete")
	public String deleteUser(@RequestParam Long id) throws NoEntryException {
		userService.delete(id);

		return "redirect:/"+UrlConstants.INDEX_ADMIN.toString().toLowerCase();
	}

	//SEARCH UserInfo
	@GetMapping("/search")
	public ModelAndView search(@RequestParam String keyword) {
		ModelAndView mav = new ModelAndView("search");
		List<UserInfo> result = userInfoService.search(keyword);
		mav.addObject("result", result);

		return mav;

	}

	//Exception Handling/////////////////////

	@ExceptionHandler(DuplicateEntryException.class)
	public ModelAndView handleException(DuplicateEntryException ex)
	{
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("message", ex.getMessage());
		mav.addObject("user", new User());
		return mav;
	}

	@ExceptionHandler(EmailFormatException.class)
	public ModelAndView handleException(EmailFormatException ex)
	{
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("message", ex.getMessage());
		mav.addObject("user", new User());
		return mav;
	}

	@ExceptionHandler(NoEntryException.class)
	public ModelAndView handleException(NoEntryException ex)
	{
		ModelAndView mav = new ModelAndView("index_admin");
		mav.addObject("message", ex.getMessage());
		mav.addObject("user", new User());
		mav.addObject("authTypes", AuthorityType.values());

		List<Authority> allAuthorities = authService.listAll();
		mav.addObject("listAuthorities", allAuthorities);
		return mav;
	}
}
