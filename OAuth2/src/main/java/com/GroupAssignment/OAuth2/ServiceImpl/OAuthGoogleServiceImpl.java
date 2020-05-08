package com.GroupAssignment.OAuth2.ServiceImpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.plus.Person;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;

import com.GroupAssignment.OAuth2.Service.OAuthGoogleService;

@Service
public class OAuthGoogleServiceImpl implements OAuthGoogleService{

	// get google id and secret value from application.properties	
	@Value("${spring.social.google.app-id}")
	private String googleId;
	@Value("${spring.social.google.app-secret}")
	private String googleSecret;
	
	// google connection create	
	private GoogleConnectionFactory CreateOAuthGoogleConnection() {
		return new GoogleConnectionFactory(googleId, googleSecret);
	}
	
	// create auth server and assign scope and redirect page
	@Override
	public String OAuthGoogleLogin() {
		OAuth2Parameters parameters = new OAuth2Parameters();
		parameters.setRedirectUri("http://localhost:3000/google");
		parameters.setScope("profile");
		return CreateOAuthGoogleConnection().getOAuthOperations().buildAuthenticateUrl(parameters);
	}

	// return access token	
	@Override
	public String getGoogleOAuthAccessToken(String code) {
		return CreateOAuthGoogleConnection().getOAuthOperations().exchangeForAccess(code, "http://localhost:3000/google", null).getAccessToken();
	}

	// return authorized user infomation from facebook server
	@Override
	public Person getGoogleUserProfile(String accessToken) {
		Google google = new GoogleTemplate(accessToken);
		Person person = google.plusOperations().getGoogleProfile();
				return person;
	}
	
}
