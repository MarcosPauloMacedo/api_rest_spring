package br.edu.imepac.atendimento.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProntuarioDTO {
    private Long registroAgenda;
    private String historico;
    private String receituario;
    private String exames;
}
