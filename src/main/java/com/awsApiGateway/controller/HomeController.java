package com.awsApiGateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.web.client.RestTemplate;

@RestController
public class HomeController {

    @Autowired
    private RestTemplate restTemplate;

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

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8082/secure", // API URL of the other Spring Boot app
                HttpMethod.GET,
                entity,
                String.class
        );
        return response.getBody();
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
