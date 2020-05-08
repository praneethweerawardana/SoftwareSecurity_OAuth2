package com.GroupAssignment.OAuth2.Service;

import org.springframework.social.google.api.plus.Person;

//interface for all facebook services

public interface OAuthGoogleService {

	String OAuthGoogleLogin();

	String getGoogleOAuthAccessToken(String code);

	Person getGoogleUserProfile(String accessToken);

	
}
