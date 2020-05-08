package com.GroupAssignment.OAuth2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.google.api.plus.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.GroupAssignment.OAuth2.Model.LoginUserInfo;
import com.GroupAssignment.OAuth2.Service.OAuthGoogleService;

@Controller
public class OAuthGoogleController {
	
	// define AuthGoogleService private property
	@Autowired private OAuthGoogleService oAuthGoogleService;
	
	//request from server
	@GetMapping(value = "/googleloginOAuth")
	public RedirectView OAuthGoogleLogin() {
		
		RedirectView redirectView = new RedirectView();
		String url = oAuthGoogleService.OAuthGoogleLogin();
		System.out.println(url);
		redirectView.setUrl(url);
		return redirectView;
	}
	
	//request to token	
	@GetMapping("/google")
	public String google(@RequestParam("code") String code) {
		String accessToken = oAuthGoogleService.getGoogleOAuthAccessToken(code);
		return "redirect:/googleProfileData/"+accessToken;
	}
		
	//get token and return user info value to UserProfile redirect page	
	@GetMapping(value = "/googleProfileData/{accessToken:.+}")
	public String googleProfileData(@PathVariable String accessToken, Model model) {
		Person person = oAuthGoogleService.getGoogleUserProfile(accessToken);
	 
		LoginUserInfo loginUserInfo = new LoginUserInfo(person.getGivenName(), person.getFamilyName(), person.getImageUrl());
       
		// print user , token and user info		
				 System.out.println("user print here = "+person);
				System.out.println("Token print here = "+accessToken);
		        System.out.println("Login user info print here = "+loginUserInfo);
		
		model.addAttribute("person", loginUserInfo);
		return "view/UserProfile";
		
	}


}
