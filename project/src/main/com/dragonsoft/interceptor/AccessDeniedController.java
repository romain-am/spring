package main.com.dragonsoft.interceptor;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccessDeniedController {
	
	@GetMapping(value="/access_denied")
	public String accessDenied(Map<String, Object> model) {
		return "access_denied";
	}

}
