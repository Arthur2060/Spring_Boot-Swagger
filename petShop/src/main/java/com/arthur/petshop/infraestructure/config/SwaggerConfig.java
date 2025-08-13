package com.arthur.petshop.infraestructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI petShopOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("PetShop")
                                .description(
                                        """
                                        API com fins educativos para prática de java com spring
                                        boot e foco em documentação com Swagger.
                                        """)
                                .contact(new Contact()
                                        .name("Arthur (Desenvolvedor)")
                                        .email("arthurhenrrique1705@gmail.com")
                                )
                );
    }
}
