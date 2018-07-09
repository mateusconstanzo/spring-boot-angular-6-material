package br.com.events.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(parameters());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Evento API")
                .contact(new Contact("Mateus Constanzo", "", "mateusconstanzo@gmail.com"))
                .build();
    }

    private List<Parameter> parameters() {
        List<Parameter> parameters = new ArrayList<>();

        Parameter headParameter = new ParameterBuilder()
                .name("Authorization")
                .description("JWT")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();

        parameters.add(headParameter);

        return parameters;
    }

}
