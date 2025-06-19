package com.awsApiGateway.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final ClientRegistrationRepository clientRegistrationRepository;

    public SecurityConfig(ClientRegistrationRepository clientRegistrationRepository) {
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // All routes require authentication
                .authorizeHttpRequests(requests ->
                        requests.anyRequest().authenticated()
                )
                // Configure OAuth2 Login
                .oauth2Login(oauth2 ->
                        oauth2
                                // Optional: customize login page if needed.
                                .defaultSuccessUrl("/home", true)
                )
                // Optionally configure logout behavior
                .logout(logout ->
                        logout
                                // a) Invalidate session & clear auth
                                .invalidateHttpSession(true)
                                .clearAuthentication(true)
                                .deleteCookies("JSESSIONID")

                                // b) Launch Cognito logout endpoint on success
                                .logoutSuccessHandler(oidcLogoutSuccessHandler())
                );
        return http.build();
    }

    private LogoutSuccessHandler oidcLogoutSuccessHandler() {
        OidcClientInitiatedLogoutSuccessHandler handler =
                new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);

        // After Cognito logout, user will be redirected here.
        // {baseUrl} expands to e.g. http://localhost:8080
        handler.setPostLogoutRedirectUri("{baseUrl}/");
        return handler;
    }


}
