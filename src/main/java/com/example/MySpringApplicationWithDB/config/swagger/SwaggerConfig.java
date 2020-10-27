package com.example.MySpringApplicationWithDB.config.swagger;

import io.swagger.models.auth.In;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.MySpringApplicationWithDB"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(List.of(tokenAuthorization()))
                .securityContexts(List.of(securityContext()))
                .apiInfo(metaData());
    }

    private ApiKey tokenAuthorization() {
        return new ApiKey("JWT",
                HttpHeaders.AUTHORIZATION,
                In.HEADER.name());
    }



    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(List.of(defaultAuth()))
                .forPaths(PathSelectors.any())
                .build();
    }

    private SecurityReference defaultAuth() {
        return SecurityReference.builder()
                .scopes(new AuthorizationScope[0])
                .reference("JWT")
                .build();
    }

    public ApiInfo metaData(){
        return new ApiInfoBuilder()
                .title("My Spring Project with database")
                .description("\"Spring Boot REST API for task\"")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE02.0\"")
                .contact(new Contact("Dmitriy Sergeev", "someMail@someDomen.con", "email@mail.ru"))
                .build();
    }
}
