package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;


/*@EnableAuthorizationServer: Enables an authorization server.AuthorizationServerEndpointsConfigurer
defines the authorization and token endpoints and the token services.*/
@Configuration
@EnableAuthorizationServer
//if you use OAUTH2 version(2.4.0) in your pom then here you will be given error as @EnableAuthorizationServer
//is depreciated it means spring security OAuth2.0 v2.x is now part of spring-security core v5.2.x. authorization server is not supported anymore 
//spring security OAuth2.0 v2.x so use instead v2.2.6.RELEASE of spring boot for spring securoty v5.2.x
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter  {

	
	static final String CLIENT_ID = "mohit-client";
	static final String CLIENT_SECRET = "$2y$12$h.c12a460rz8gBbnRs3kBuaz41t0JiZp/JX8oYDP..0k33N.xdDEu";
	static final String GRANT_TYPE = "password";
	static final String AUTHORIZATION_CODE = "authorization_code";
	static final String REFRESH_TOKEN = "refresh_token";
	static final String IMPLICIT = "implicit";
	static final String SCOPE_READ = "read";
	static final String SCOPE_WRITE = "write";
	static final String TRUST = "trust";
	static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1*60*60;
    static final int FREFRESH_TOKEN_VALIDITY_SECONDS = 6*60*60;
	
	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
    private UserApprovalHandler userApprovalHandler;//A user approval handler that remembers approval decisions by consulting existing approvals.
	
	
	//here in below method we could have used configurer.withClientDetails(customClientDetailsService)
	// to aunthentify OAUTH2 client instead of using inMemory() method..in this authorization server we are
	//using harcoded client crendentials and we can use customClientDetailsService to create our own user details
	//and save it db..refrences are below
	// https://blog.couchbase.com/oauth-2-dynamic-client-registration/
	//https://stackoverflow.com/questions/40866037/spring-security-oauth2-custom-clientdetailsservice
	@Override
	public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {

		configurer
				.inMemory()
				.withClient(CLIENT_ID)
				.secret(CLIENT_SECRET)
				.authorizedGrantTypes(GRANT_TYPE, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT )
				.scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
				.accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS).
				refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore).userApprovalHandler(userApprovalHandler)
				.authenticationManager(authenticationManager);
	}
}


/*
 * this class extends AuthorizationServerConfigurerAdapter and is responsible
 * for generating tokens specific to a client.Suppose, if a user wants to login
 * to mohit.com via facebook then facebook auth server will be generating
 * tokens for a website/client.In this case, website/client becomes the client which will be
 * requesting for authorization code on behalf of user from facebook - the
 * authorization server. Following is a similar implementation that facebook
 * will be using.
 */