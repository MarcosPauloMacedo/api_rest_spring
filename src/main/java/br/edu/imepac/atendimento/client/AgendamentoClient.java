package br.edu.imepac.atendimento.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "agendamento-service", url = "https://my-json-server.typicode.com/MarcosPauloMacedo/fake_api_teste", fallbackFactory = AgendamentoFallback.class)
public interface AgendamentoClient {
    
    @GetMapping("/consulta")
    Consulta buscarConsulta();
}
