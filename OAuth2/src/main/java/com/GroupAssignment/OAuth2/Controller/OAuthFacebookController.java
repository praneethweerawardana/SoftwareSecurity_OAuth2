package com.GroupAssignment.OAuth2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.GroupAssignment.OAuth2.Model.LoginUserInfo;
import com.GroupAssignment.OAuth2.Service.OAuthFacebookService;

@Controller
public class OAuthFacebookController {
	
	// define AuthFacebookService private property
	@Autowired private OAuthFacebookService oAuthFacebookService;
	
	//request from server	
	@GetMapping(value = "/facebookloginOAuth")
	public RedirectView OAuthFacebookLogin() {
		
		RedirectView redirectView = new RedirectView();
		String url = oAuthFacebookService.OAuthFacebookLogin();
		System.out.println(url);
		redirectView.setUrl(url);
		return redirectView;
	}
	
	//request to token	
	@GetMapping("/facebookApp")
	public String facebookApp(@RequestParam("code") String code) {
		String accessToken = oAuthFacebookService.getFacebookOAuthAccessToken(code);
		return "redirect:/facebookProfileData/"+accessToken;
	}
		
	//get token and return user info value to UserProfile redirect page 	
	@GetMapping(value = "/facebookProfileData/{accessToken:.+}")
	public String facebookProfileData(@PathVariable String accessToken, Model model) {
		User user = oAuthFacebookService.getFacebookUserProfile(accessToken);
	   
		LoginUserInfo loginUserInfo = new LoginUserInfo(user.getFirstName(), user.getLastName(), user.getEmail());
		
		// print user , token and user info		
		 System.out.println("user print here = "+user);
		System.out.println("Token print here = "+accessToken);
        System.out.println("Login user info print here = "+loginUserInfo);
	 
	    model.addAttribute("user", loginUserInfo);
		return "view/UserProfile";
		
	}

}
