package com.volgsys.api.SwaggerConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yaml.snakeyaml.Yaml;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(Collections.singletonList(apiKey()))
                .securityContexts(Collections.singletonList(securityContext()));
    }

    private ApiInfo apiInfo() {
        try (InputStream in = getClass().getResourceAsStream("/swagger.yaml")) {
            Yaml yaml = new Yaml();
            // Carrega o conteúdo do arquivo YAML como uma string
            Map<String, Object> yamlMap = yaml.load(in);
            if (yamlMap.containsKey("info")) {
                Map<String, Object> infoMap = (Map<String, Object>) yamlMap.get("info");
                String title = (String) infoMap.get("title");
                String description = (String) infoMap.get("description");
                String version = (String) infoMap.get("version");
                return new ApiInfoBuilder()
                        .title(title)
                        .description(description)
                        .version(version)
                        // Adiciona outras informações personalizadas, se necessário
                        //.contact(new Contact("Nome do contato", "URL do contato", "Email do contato"))
                        //.license("Licença")
                        //.licenseUrl("URL da licença")
                        .build();
            }
        } catch (Exception e) {
            // Tratar exceção de leitura do arquivo YAML
            e.printStackTrace();
        }
        return new ApiInfoBuilder().build();
    }

    private SecurityScheme apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections.singletonList(
                new SecurityReference("JWT", authorizationScopes));
    }
}
