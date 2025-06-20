package com.awsApiGateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;

@RestController
public class HomeController {

    private final OAuth2AuthorizedClientService authorizedClientService;
    public HomeController(OAuth2AuthorizedClientService _authorizedClientService){
        this.authorizedClientService = _authorizedClientService;
    }

    @GetMapping("/home")
    public String getAccessToken(OAuth2AuthenticationToken authentication) {

        OAuth2AuthorizedClient authorizedClient = authorizedClientService
                .loadAuthorizedClient(
                        authentication.getAuthorizedClientRegistrationId(),
                        authentication.getName()
                );

        String accessToken = authorizedClient.getAccessToken().getTokenValue();
        System.out.println("Access Token: " + accessToken);
        return "tokenView";
    }

    @GetMapping("/")
    public String index() {
        return "index"; // Corresponds to src/main/resources/templates/index.html
    }

    @GetMapping("/about")
    public String callback() {
        return "about"; // Corresponds to src/main/resources/templates/home.html
    }

    @GetMapping("/dd")
    public String tokens() {
        return "vikash"; // Corresponds to src/main/resources/templates/home.html
    }
}
