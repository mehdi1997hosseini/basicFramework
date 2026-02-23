package ir.mehdihosseini.basicframework.base.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerSecurityConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(new Info().title("basic framework"))
                .addSecurityItem(new SecurityRequirement().addList("security"))
                .components(new Components().addSecuritySchemes("security", new SecurityScheme()
                        .name("Authorization").type(SecurityScheme.Type.HTTP).scheme("Bearer").bearerFormat("JWT")));

    }

}
