package br.com.projeto.contratados.config.docs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//OpenAPI
//OData

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.getInfoApi())
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.projeto.contratados.rest.controller"))
                .build();
    }

    @Bean
    public UiConfiguration uiConfiguration(){
        return UiConfigurationBuilder.builder()
                .filter(true)
                .docExpansion(DocExpansion.LIST)
                .defaultModelRendering(ModelRendering.EXAMPLE)
                .deepLinking(true)
                .defaultModelsExpandDepth(1)
                .defaultModelExpandDepth(1)
                .tagsSorter(TagsSorter.ALPHA)
                .operationsSorter(OperationsSorter.ALPHA)
                .displayRequestDuration(true)
                .showExtensions(true)
                .build();
    }

    private ApiInfo getInfoApi(){
        return new ApiInfoBuilder()
                .title("Sistema de Contratação API")
                .description("Documentação das APIs de Contratados")
                .version("1.0.0")
                .build();
    }
}
