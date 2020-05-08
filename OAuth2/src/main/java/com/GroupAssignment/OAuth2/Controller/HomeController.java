package com.GroupAssignment.OAuth2.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	//create rest controller for url mapping
	
	//redirect to home page
	@GetMapping(value = "/")
	public String HomeView() {
		return "view/index";		
	}
	
	//logout button controller mapping. when 
	@GetMapping(value="/logout")
	public String Logout() {
		return "view/index";
		
	}

}
