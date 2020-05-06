package com.example.demo.security.oauth.user;

import java.util.Map;

import com.example.demo.entities.AuthProvider;
import com.example.demo.exception.OAuth2AuthenticationProcessingException;

public class OAuth2UserInfoFactory {

	    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
	        if (registrationId.equalsIgnoreCase(AuthProvider.github.toString())) {
	            return new GithubOAuth2UserInfo(attributes);
	        } else {
	            throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
	        }
	    }
	}

