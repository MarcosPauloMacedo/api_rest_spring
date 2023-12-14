package br.edu.imepac.atendimento.config;

import org.springframework.stereotype.Component;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;

@Component
public class ConfigService {
    
    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Clínica Médica - Atendimento")
            .description("O módulo de atendimento é responsável por gerenciar os atendimentos dos pacientes.")
            .version("0.0.1")
            .build();
    }
}
