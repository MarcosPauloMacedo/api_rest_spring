package br.edu.imepac.atendimento.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.edu.imepac.atendimento.client.AgendamentoClient;
import br.edu.imepac.atendimento.client.Consulta;
import br.edu.imepac.atendimento.exceptions.NotFoundExceptions;
import br.edu.imepac.atendimento.model.dto.ProntuarioDTO;
import br.edu.imepac.atendimento.model.entity.Prontuario;
import br.edu.imepac.atendimento.model.mapper.ProntuarioMapper;
import br.edu.imepac.atendimento.model.repository.ProntuarioRepository;
import br.edu.imepac.atendimento.model.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProntuarioService {

    private final ProntuarioRepository prontuarioRepository;
    private final AgendamentoClient agendamentoClient;
    private final ProntuarioMapper prontuarioMapper;
    private final Response response;

    /**
     * Cadastrar um prontuario
     * @param prontuarioDTO
     * @return Response
     */
    public Response cadastrarProntuario(ProntuarioDTO prontuarioDTO) {

        log.info("Cadastrando prontuario: {}", prontuarioDTO);
        consultaExiste();
        Prontuario prontuario = prontuarioMapper.toEntity(prontuarioDTO);
        prontuarioRepository.save(prontuario);

        response.setStatus(HttpStatus.CREATED.value());
        response.setMensagem("Prontuario cadastrado com sucesso");
        response.setProntuario(prontuarioDTO);

        return response;
    }

    /**
     * Buscar todos os prontuarios
     * @return List<ProntuarioDTO>
     */
    public List<ProntuarioDTO> buscarTodosProntuarios() {
        
        log.info("Buscando todos os prontuarios");
        List<Prontuario> prontuarios = prontuarioRepository.findAll();
        List<ProntuarioDTO> prontuarioDTOs = prontuarioMapper.toDTOList(prontuarios);
      
        return prontuarioDTOs;
    }

    /**
     * Visualizar um prontuario
     * @param id
     * @return ProntuarioDTO
     */
    public ProntuarioDTO visualizarProntuario (Long id) {

        prontuarioExiste(id);
        Prontuario prontuario = prontuarioRepository.findById(id).get();
        ProntuarioDTO prontuarioDTO = prontuarioMapper.toDTO(prontuario);
        return prontuarioDTO;
    }

    /**
     * Editar um prontuario
     * @param id
     * @param prontuarioDTO
     * @return Response
     */
    public Response editarProntuario(Long id, ProntuarioDTO prontuarioDTO) {

        prontuarioExiste(id);
        consultaExiste();
        Prontuario prontuario = prontuarioMapper.toEntity(prontuarioDTO, id);
        prontuarioRepository.save(prontuario);
        
        response.setStatus(HttpStatus.OK.value());
        response.setMensagem("Prontuario editado com sucesso");
        response.setProntuario(prontuarioDTO);

        return response;
    }

    /**
     * Excluir um prontuario
     * @param id
     * @return No_Content
     */
    public ResponseEntity<Object> excluirProntuario(Long id) {

        prontuarioExiste(id);
        Prontuario prontuario = prontuarioRepository.findById(id).get();
        prontuarioRepository.delete(prontuario);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Buscar consulta
     * @return Consulta
     */
    public Consulta buscarConsulta() {
        return agendamentoClient.buscarConsulta();
    }

    /**
     * Verificar se o prontuario existe
     * @param id
     * @throws NotFoundExceptions
     */
    private void prontuarioExiste(Long id) {
        if(!prontuarioRepository.existsById(id)) {
            throw new NotFoundExceptions("Prontuario não encontrado");
        }
    }

    /**
     * Verificar se a consulta existe
     * @throws NotFoundExceptions
     */
    private void consultaExiste() {

        if(buscarConsulta() == null) {
            throw new NotFoundExceptions("Consulta não encontrada");
        }
    }
}
