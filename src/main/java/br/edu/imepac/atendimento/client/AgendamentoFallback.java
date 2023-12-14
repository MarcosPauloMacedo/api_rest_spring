package br.edu.imepac.atendimento.client;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class AgendamentoFallback implements FallbackFactory<AgendamentoClient> {

    Map<Object, Object> usuario;
    Map<Object, Object> paciente;
    Map<Object, Object> medico;
    
    public AgendamentoFallback() {

        this.usuario = new HashMap<>();
        usuario.put("id", 1L);
        usuario.put("nome", "Usuário");

        this.paciente = new HashMap<>();
        paciente.put("id", 1L);
        paciente.put("nome", "Paciente");

        this.medico = new HashMap<>();
        medico.put("id", 1L);
        medico.put("nome", "Médico");
    }
    
    @Override
    public AgendamentoClient create(Throwable cause) {
        return new AgendamentoClient() {
            
            @Override
            public Consulta buscarConsulta() {
                Consulta consulta = new Consulta();
                consulta.setId(1L);
                consulta.setUsuario(usuario);
                consulta.setPaciente(paciente);
                consulta.setMedico(medico);
                consulta.setData(Date.valueOf("2021-01-01"));
                consulta.setHora("10:00");
                consulta.setRetorno("Não");
                consulta.setCancelamento("Não");
                consulta.setMotivoCancelamento("nenhum");
                return consulta;
            }
        };
    }
}
