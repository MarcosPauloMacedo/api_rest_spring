package br.edu.imepac.atendimento.exceptions;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {
    private int status;
    private String mensagem;
    private Date data;
    private String caminho;
    private String descricao;
}
