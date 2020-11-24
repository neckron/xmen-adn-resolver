package com.xmen.dna.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This class implements configuration for Swagger
 * @author fr.rodriguez
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xmen.dna"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo())
                .pathMapping("/");
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo("Xmen DNA Mutant Detection API",
                "This is a simple API to find if a DNA sequence can hold mutant DNA",
                "V1",
                null,
                new Contact("Francisco Rodríguez", null, "neckron@gmail.com"),
                null,
                null,
                Collections.emptyList()
        );

    }

}
