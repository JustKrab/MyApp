package com.example.myapp.configs;

import com.example.myapp.repos.UserRepo;
import com.example.myapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@Configuration
@EnableWebSecurity
@EnableOAuth2Client
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Autowired
    private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    @Autowired
    private OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    @Autowired
    private HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }

    @Bean
    public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/registration", "/static/**","/review/*","/login","/src").permitAll()
                .antMatchers("/review/**/edit","/review/**/new","/review/**/delete").authenticated()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/profile")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .oauth2Login()
                    .authorizationEndpoint()
                        .baseUri("/oauth2/authorize")
                        .authorizationRequestRepository(cookieAuthorizationRequestRepository())
                        .and()
                    .redirectionEndpoint()
                        .baseUri("/oauth2/callback/*")
                        .and()
                    .userInfoEndpoint()
                        .userService(customOAuth2UserService)
                        .and()
                    .successHandler(oAuth2AuthenticationSuccessHandler)
                    .failureHandler(oAuth2AuthenticationFailureHandler);

        // Add our custom Token based authentication filter
        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

//    @Bean
//    public PrincipalExtractor principalExtractor(UserRepo userRepo) {
//        return map -> {
//            Long id = (Long) map.get("sub");
//            User user = userRepo.findById(id).orElseGet(() -> {
//
//                User newUser = new User();
//                newUser.setId(id);
//                newUser.setUsername((String) map.get("name"));
//                newUser.setEmail((String) map.get("email"));
//                newUser.setRoles(Collections.singleton(Role.USER));
//                newUser.setActive(true);
//                newUser.setAvatar("user_r0qibf.png");
//
//                return newUser;
//            });
//            userRepo.save(user);
//            return user;
//        };
//    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}