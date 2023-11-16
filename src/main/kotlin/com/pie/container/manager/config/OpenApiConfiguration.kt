package com.pie.container.manager.config
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * OpenApiConfiguration
 */
@Configuration
class OpenApiConfiguration {
    @Bean
    fun openApi() : OpenAPI = OpenAPI()
        .info(
            Info()
                .title("container-manager")
                .description("Endpoints to manage containers")
                .version("0.0.1")
        )
}
