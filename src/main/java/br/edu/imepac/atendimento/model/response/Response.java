package br.edu.imepac.atendimento.model.response;

import org.springframework.stereotype.Component;

import br.edu.imepac.atendimento.model.dto.ProntuarioDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Response {
    private int status;
    private String mensagem;
    private ProntuarioDTO prontuario;
}
