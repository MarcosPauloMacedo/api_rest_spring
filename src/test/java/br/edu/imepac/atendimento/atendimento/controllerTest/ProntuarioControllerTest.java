package br.edu.imepac.atendimento.atendimento.controllerTest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.imepac.atendimento.client.Consulta;
import br.edu.imepac.atendimento.controller.ProntuarioController;
import br.edu.imepac.atendimento.model.dto.ProntuarioDTO;
import br.edu.imepac.atendimento.model.response.Response;
import br.edu.imepac.atendimento.model.service.ProntuarioService;

@ExtendWith(MockitoExtension.class)
public class ProntuarioControllerTest {

    @InjectMocks
    private ProntuarioController prontuarioController;

    @Mock
    private ProntuarioService prontuarioService;

    @Mock
    private Response response;

    @Mock
    private ProntuarioDTO prontuarioDTO;

    @Mock
    private Consulta consulta;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        prontuarioDTO = new ProntuarioDTO(01L,"historico", "receituario", "exames");

        mockMvc = MockMvcBuilders.standaloneSetup(prontuarioController).build();

        consulta = new Consulta();
        objectMapper = new ObjectMapper();
        response = new Response();
        response.setMensagem("Teste concluído com sucesso!");
        response.setProntuario(prontuarioDTO);
    }

    /**
     * Teste de cadastro de prontuario
     * @throws Exception
     */
    @Test
    public void cadastrarProntuarioTest() throws Exception {

        response.setStatus(HttpStatus.CREATED.value());
        when(prontuarioService.cadastrarProntuario(prontuarioDTO)).thenReturn(response);

        mockMvc.perform(post("/prontuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(prontuarioDTO)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.mensagem").value(response.getMensagem()))
            .andExpect(jsonPath("$.status").value(response.getStatus()))
            .andExpect(jsonPath("$.prontuario").value(response.getProntuario()));
    }

    /**
     * Teste de busca de todos os prontuarios
     * @throws Exception
     */
    @Test
    public void buscarTodosProntuariosTest() throws Exception {
        List<ProntuarioDTO> prontuarios = new ArrayList<>();
        prontuarios.add(prontuarioDTO);

        when(prontuarioService.buscarTodosProntuarios()).thenReturn(prontuarios);
        mockMvc.perform(get("/prontuarios"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].registroAgenda").value(prontuarioDTO.getRegistroAgenda()));
    }

    /**
     * Teste de visualização de prontuario
     * @throws Exception
     */
    @Test
    public void visualizarProntuarioTest() throws Exception {
        when(prontuarioService.visualizarProntuario(01L)).thenReturn(prontuarioDTO);

        mockMvc.perform(get("/prontuarios/01"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.registroAgenda").value(prontuarioDTO.getRegistroAgenda()));
    }

    /**
     * Teste de edição de prontuario
     * @throws Exception
     */
    @Test
    public void editarProntuarioTest() throws Exception {

        response.setStatus(HttpStatus.OK.value());
        when(prontuarioService.editarProntuario(01L, prontuarioDTO)).thenReturn(response);

        mockMvc.perform(put("/prontuarios/01")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(prontuarioDTO)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.mensagem").value(response.getMensagem()))
            .andExpect(jsonPath("$.status").value(response.getStatus()))
            .andExpect(jsonPath("$.prontuario").value(response.getProntuario()));
    }

    /**
     * Teste de exclusão de prontuario
     * @throws Exception
     */
    @Test
    public void excluirProntuarioTest() throws Exception {
        ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.NO_CONTENT);

        when(prontuarioService.excluirProntuario(01L)).thenReturn(response);

        mockMvc.perform(delete("/prontuarios/01"))
            .andExpect(status().isNoContent());
    }

    /**
     * Teste de busca de todas as consultas
     * @throws Exception
     */
    @Test
    public void buscarTodasConsultasTest() throws Exception {
        when(prontuarioService.buscarConsulta()).thenReturn(consulta);

        mockMvc.perform(get("/prontuarios/consultas"))
            .andExpect(status().isOk());
    }
}
