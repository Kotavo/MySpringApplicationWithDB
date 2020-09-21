package com.example.MySpringApplicationWithDB.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

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
                .apiInfo(metaData());
    }

    public ApiInfo metaData(){
        return new ApiInfoBuilder()
                .title("My Spring Project with database")
                .description("\"Spring Boot REST API for task\"")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE02.0\"")
                .contact(new Contact("Dmitry Sergeev", "someMail@someDomen.con", "email@mail.ru"))
                .build();
    }
}
