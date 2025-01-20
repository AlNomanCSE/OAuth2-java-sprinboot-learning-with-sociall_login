package com.noman.springOAuth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(request -> request.requestMatchers("/secure").authenticated())
                .formLogin(Customizer.withDefaults())
                .oauth2Login(Customizer.withDefaults());
        return httpSecurity.build();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        ClientRegistration gitHubRegistration = githubClientRegistration();
        ClientRegistration facebookRegistration = faceookClientRegistration();
        return new InMemoryClientRegistrationRepository(gitHubRegistration, facebookRegistration);
    }

    private ClientRegistration githubClientRegistration() {
        return CommonOAuth2Provider.GITHUB.getBuilder("github").clientId("Ov23li3ZJ4k6y3EyiyXL").clientSecret("5c03972136df3d398121230e728be814e05f7307").build();
    }

    private ClientRegistration faceookClientRegistration() {
        return CommonOAuth2Provider.FACEBOOK.getBuilder("faceook").clientId("619153863854025").clientSecret("d1f2e85f282c52d3678d9af7fd46247d").build();
    }
}
