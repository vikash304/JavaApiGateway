package com.awsApiGateway.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "index"; // Corresponds to src/main/resources/templates/index.html
    }

    // Secured home page, accessible only after authentication
    @GetMapping("/home")
    public String home() {
        return "home"; // Corresponds to src/main/resources/templates/home.html
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
