package org.example.userregistration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication()
public class UserRegistrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserRegistrationApplication.class, args);
    }

}
