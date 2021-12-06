package com.example.myapp.services;

import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public interface CustomOidcUserService  {

    OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException;

    OidcUser processOidcUser(OidcUserRequest userRequest, OidcUser oidcUser);

}
