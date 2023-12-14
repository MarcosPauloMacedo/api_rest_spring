package br.edu.imepac.atendimento.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.imepac.atendimento.client.Consulta;
import br.edu.imepac.atendimento.model.dto.ProntuarioDTO;
import br.edu.imepac.atendimento.model.response.Response;
import br.edu.imepac.atendimento.model.service.ProntuarioService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/prontuarios")
public class ProntuarioController {

    private final ProntuarioService prontuarioService;

    /**
     * Cadastrar um prontuario
     * @param prontuario
     * @return Status 201 Body Response
     */
    @PostMapping
    @Operation(summary = "Cadastrar um prontuário")
    public ResponseEntity<Object> cadastrarProntuario(@RequestBody ProntuarioDTO prontuario) {

        Response response = prontuarioService.cadastrarProntuario(prontuario);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    /**
     * Buscar todos os prontuarios
     * @return List<ProntuarioDTO>
     */
    @GetMapping
    @Operation(summary = "Buscar todos os prontuários")
    public List<ProntuarioDTO> buscarTodosProntuarios() {

        return prontuarioService.buscarTodosProntuarios();
    }

    /**
     * Visualizar um prontuario
     * @param id
     * @return ProntuarioDTO
     */
    @GetMapping("/{id}")
    @Operation(summary = "Visualizar um prontuário")
    public ProntuarioDTO visualizarProntuario(@PathVariable Long id) {

        return prontuarioService.visualizarProntuario(id);
    }

    /**
     * Editar um prontuario
     * @param id
     * @param prontuario
     * @return Status 200 Body Response
     */
    @PutMapping("/{id}")
    @Operation(summary = "Editar um prontuário")
    public ResponseEntity<Object> editarProntuario(@PathVariable Long id, @RequestBody ProntuarioDTO prontuario){
        
        Response response = prontuarioService.editarProntuario(id, prontuario);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    /**
     * Excluir um prontuario
     * @param id
     * @return No_Content
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um Prontuario")
    public ResponseEntity<Object> excluirProntuario(@PathVariable Long id){
        
        return prontuarioService.excluirProntuario(id);
    }

    /**
     * Buscar todas as consultas "Dados fakes para teste do feign"
     * @return Consulta
     */
    @GetMapping("/consultas")
    @Operation(summary = "Buscar todas as consultas")
    public Consulta buscarTodasConsultas() {

        return prontuarioService.buscarConsulta();
    }
}