package br.edu.imepac.atendimento.client;

import java.sql.Date;
import java.util.Map;

import lombok.Data;

@Data
public class Consulta {
    private Long id;
    private Map<Object, Object> usuario;
    private Map<Object, Object> paciente;
    private Map<Object, Object> medico;
    private Date data;
    private String hora;
    private String retorno;
    private String cancelamento;
    private String motivoCancelamento;
}
