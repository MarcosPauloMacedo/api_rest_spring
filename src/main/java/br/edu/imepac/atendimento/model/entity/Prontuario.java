package br.edu.imepac.atendimento.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "atendimento", name = "prontuario_paciente")
public class Prontuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "registro_agenda")
    @NotNull(message = "O registro da agenda não pode ser nulo")
    private Long registroAgenda;

    @Column(name = "historico")
    @NotNull(message = "O historico não pode ser nulo")
    private String historico;

    @Column(name = "receituario")
    @NotNull(message = "O receituario não pode ser nulo")
    private String receituario;

    @Column(name = "exames")
    @NotNull(message = "O exames não pode ser nulo")
    private String exames;
}
