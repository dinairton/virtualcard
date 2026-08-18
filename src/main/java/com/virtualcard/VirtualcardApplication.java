package com.virtualcard;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "Virtual Card API",
                version = "0.0.1-SNAPSHOT",
                description = "Virtual Card Project"
        )
)
@SpringBootApplication
public class VirtualcardApplication {

    public static void main(String[] args) {
        SpringApplication.run(VirtualcardApplication.class, args);
    }

}
