package com.volgsys.api;

import io.swagger.models.resourcelisting.ApiInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@SpringBootApplication
// Adjust the package name accordingly
    public class CepApp {

        public static void main(String[] args) {
            //SpringApplication.run(CepApp.class, args);

            SpringApplication springApplication = new SpringApplication(CepApp.class);
            springApplication.run(args);
        }

    }

