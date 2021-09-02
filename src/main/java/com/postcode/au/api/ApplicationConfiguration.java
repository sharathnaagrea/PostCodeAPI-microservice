package com.postcode.au.api;

import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ApplicationConfiguration {
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiDetails()).select()
                .apis(RequestHandlerSelectors.basePackage("com.postcode.au.api.controller"))
                .paths(PathSelectors.ant("/api/**")).build();

    }

    private ApiInfo apiDetails() {
        return new ApiInfoBuilder().title("Post Code API Micro Services").description("Post Code API Micro Services")
                .version("1.0").build();
    }
}
