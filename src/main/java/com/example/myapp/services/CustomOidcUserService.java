package com.example.myapp.services;

import com.example.myapp.configs.UserGoogleInfo;
import com.example.myapp.entityes.Role;
import com.example.myapp.entityes.User;
import com.example.myapp.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class CustomOidcUserService extends OidcUserService {
    @Autowired
    private UserRepo userRepository;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);

        try {
            return processOidcUser(userRequest, oidcUser);
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OidcUser processOidcUser(OidcUserRequest userRequest, OidcUser oidcUser) {
        UserGoogleInfo googleUserInfo = new UserGoogleInfo(oidcUser.getAttributes());

        // see what other data from userRequest or oidcUser you need

        Optional<User> userOptional = userRepository.findByEmail(googleUserInfo.getEmail());
        if (!userOptional.isPresent()) {
            User user = new User();
            user.setEmail(googleUserInfo.getEmail());
            user.setUsername(googleUserInfo.getName());
            user.setActive(true);
            user.setPassword("123");
            user.setAvatar("user_r0qibf.png");
            user.setRoles(Collections.singleton(Role.USER));
            userRepository.save(user);
        }

        return oidcUser;
    }
}

