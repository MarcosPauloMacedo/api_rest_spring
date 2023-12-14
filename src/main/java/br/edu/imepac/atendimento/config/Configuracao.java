package br.edu.imepac.atendimento.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@RequiredArgsConstructor
public class Configuracao {

    private final ConfigService configService;

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    } 

    @Bean
    Docket docket() {

        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("br.edu.imepac.atendimento.controller"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(configService.apiInfo());
    }
}
