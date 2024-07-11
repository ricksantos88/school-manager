package tech.doit.app.school_manager.infrastructure.documentation

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfiguration {

    @Bean
    fun customOpenApi(): OpenAPI {
        return OpenAPI().info(
            Info()
                .title("School Manager API")
                .version("v1")
                .description("API to management of schools")
                .termsOfService("00.00.00")
                .license(License().name("00.00.00"))
        )
    }

}