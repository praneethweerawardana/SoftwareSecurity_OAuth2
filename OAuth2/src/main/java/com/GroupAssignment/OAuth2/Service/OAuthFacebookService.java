package com.GroupAssignment.OAuth2.Service;

import org.springframework.social.facebook.api.User;

// interface for all facebook services

public interface OAuthFacebookService {

	String OAuthFacebookLogin();

	String getFacebookOAuthAccessToken(String code);

	User getFacebookUserProfile(String accessToken);

}
