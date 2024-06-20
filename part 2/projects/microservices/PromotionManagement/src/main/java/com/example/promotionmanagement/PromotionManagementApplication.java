package com.example.promotionmanagement;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Promotion API", version = "2.0", description = "Promotion Information"))

public class PromotionManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(PromotionManagementApplication.class, args);
    }

}
