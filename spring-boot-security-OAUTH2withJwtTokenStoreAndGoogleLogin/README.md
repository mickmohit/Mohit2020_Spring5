#OAuth2 Login Flow

1. The OAuth2 login flow will be initiated by the frontend client by sending the user to the endpoint http://localhost:8085/oauth2/authorize/{provider}?redirect_uri=<redirect_uri_after_login>.
   The provider path parameter is one of google, facebook, or github. The redirect_uri is the URI to which the user will be redirected once the authentication with the OAuth2 provider is successful.
   
2. On receiving the authorization request, Spring Security’s OAuth2 client will redirect the user to the AuthorizationUrl of the supplied provider. 
   All the state related to the authorization request is saved using the #customAuthorizationRequestRepository specified in the SecurityConfig.
   The user now allows/denies permission to your app on the provider’s page. If the user allows permission to the app, the provider will redirect the user to the callback url http://localhost:8085/oauth2/callback/{provider} with an authorization code. If the user denies the permission, he will be redirected to the same callbackUrl but with an error.
   
3. If the OAuth2 callback results in an error, Spring security will invoke the failure handler.

4. (Main part in spring backend)If the OAuth2 callback is successful and it contains the authorization code, Spring Security will exchange the authorization_code for an access_token and invoke the #CustomOidcUserService(it is session based here) specified in the above SecurityConfig.
	
	a. When the user clicks on “Sign on using Google,” you’ll open https://**/oauth2/authorization/google in the browser.
	b. then Spring Security has a #OAuth2AuthorizationRequestRedirectFilter, which intercepts requests of the pattern /oauth2/authorization/*, and does the following:
       
	   # Creates an #OAuth2AuthorizationRequest object.
       # Stores above object using a configured AuthorizationRequestRepository.
	   # Redirects the browser to the provider’s authorization-uri page (e.g. https://accounts.google.com/o/oauth2/v2/auth), with a few parameters. Among the parameters would be a callback URL (e.g. http://localhost:8085/oauth2/callback/google).
	   # As Spring provides a session-based implementation of AuthorizationRequestRepository, so we have used session based implementation in our project. That’ll not work if your backend is stateless, because there’ll be no session. So, you’ll need to code an alternative implementation. For a cookies based implementation, refer to Spring Lemon’s HttpCookieOAuth2AuthorizationRequestRepository.
	   # After Spring redirects the user to the authorization-uri page of the provider (e.g. Google), the provider takes over and Asks the user to login if they wouldn’t have.
         and Seeks permissions from the user to allow our application to access their data (only for first time use).
	   # Redirects the browser to the callback URL (e.g. http://localhost:8085/oauth2/callback/google), along with some parameters, e.g. accessToken and state.
	   # In the backend, Spring’s #OAuth2LoginAuthenticationFilter intercepts the above request callback (e.g. http://localhost:8085/oauth2/callback/google), and does following
	     
		 ## Receives the parameters, e.g. accessToken and state.
		 ## Retrieves the OAuth2AuthorizationRequest saved earlier, using the configured AuthorizationRequestRepository discussed earlier, and does things like matching the state.
		 ## Calls a configured OAuth2UserService implementation to retrieve the user information (email etc.) by calling the user-info-uri of the provider (e.g. https://www.googleapis.com/oauth2/v3/userinfo).
		 ## Authenticates the user.
		 ## Clears the stored OAuth2AuthorizationRequest.
		 ## Redirects the user to a configured success-url i.e. redirected back to the front-end. 
		 ## The front-end then could call your backend to get the currently logged-in user, if your backend is stateful. But that won’t work when the backend is stateless.
		 ## A way to fix previous point would be to embed a short-lived JWT in the success-url above, which the front-end could exchange for a longer-term authorization token. For example, refer to Spring Lemon’s #OAuth2AuthenticationSuccessHandler. That builds a success-url like https://www.example.com/social-login-success?token=a-short-lived-jwe-auth-token.
		 ## After getting a nonce or short-lived token in the above step, the front-end then 
			  . Calls your backend to fetch the user information and a long-lived token.
			  . Updates the user information in the front-end models.
			  . Stores the long-lived token in the local storage.
		
5. The #CustomOidcUserService retrieves the details of the authenticated user and creates a new entry in the database or updates the existing entry with the same email.

6. Finally, the oAuth2AuthenticationSuccessHandler is invoked. It creates a JWT authentication token for the user and sends the user to the redirect_uri along with the JWT token in a query string.   