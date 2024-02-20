package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HomeController {


    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @GetMapping("/home")
    public String home(OAuth2AuthenticationToken authentication){
        OAuth2AuthorizedClient authorizedClient =
                this.authorizedClientService.loadAuthorizedClient(
                        authentication.getAuthorizedClientRegistrationId(),
                        authentication.getName());

        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();

        System.out.println("accessToken = " + accessToken);
        System.out.println("accessToken.getTokenValue() = " + accessToken.getTokenValue());

        return "Hello User";
    }
}
