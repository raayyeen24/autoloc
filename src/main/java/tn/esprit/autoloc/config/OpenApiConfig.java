package tn.esprit.autoloc.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "AutoLoc API", version = "1.0",
        description = "Car rental CRUD API"))
public class OpenApiConfig {
}
