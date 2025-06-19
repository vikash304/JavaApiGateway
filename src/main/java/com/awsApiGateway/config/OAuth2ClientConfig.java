/*
package com.awsApiGateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

@Configuration
public class OAuth2ClientConfig {

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        ClientRegistration cognito = ClientRegistration.withRegistrationId("cognito")
                .clientId("l000tnbh30nu6jvqp39qajdkj")
                .clientSecret("1nr7rpbcihp44ad4ma069vnlfe7rv2b2ohfi9q0308ppec2hhb8f")
                .scope("openid", "email", "profile")
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("{baseUrl}/login/oauth2/code/cognito")
                // either issuerUri _or_ all three of authorizationUri, tokenUri, jwkSetUri:
                .issuerUri("https://cognito-idp.ap-south-1.amazonaws.com/ap-south-1_pQ0Du8sGa")
                // .authorizationUri("https://your-domain/oauth2/authorize")
                // .tokenUri("https://your-domain/oauth2/token")
                // .jwkSetUri("https://your-domain/.well-known/jwks.json")
                .build();

        return new InMemoryClientRegistrationRepository(cognito);
    }
}*/
