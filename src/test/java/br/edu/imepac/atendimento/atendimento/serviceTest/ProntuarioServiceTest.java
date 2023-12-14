package br.edu.imepac.atendimento.atendimento.serviceTest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.edu.imepac.atendimento.client.AgendamentoClient;
import br.edu.imepac.atendimento.client.Consulta;
import br.edu.imepac.atendimento.model.dto.ProntuarioDTO;
import br.edu.imepac.atendimento.model.entity.Prontuario;
import br.edu.imepac.atendimento.model.mapper.ProntuarioMapper;
import br.edu.imepac.atendimento.model.repository.ProntuarioRepository;
import br.edu.imepac.atendimento.model.response.Response;
import br.edu.imepac.atendimento.model.service.ProntuarioService;

@ExtendWith(MockitoExtension.class)
public class ProntuarioServiceTest {
    
    @InjectMocks
    private ProntuarioService prontuarioService;

    @Mock
    private ProntuarioRepository prontuarioRepository;

    @Mock
    private ProntuarioDTO prontuarioDTO;

    @Mock
    private Prontuario prontuario;

    @Mock
    private Consulta consulta;

    @Mock
    private AgendamentoClient agendamentoClient;

    @Mock
    private ProntuarioMapper prontuarioMapper;

    @Mock 
    private Response response;

    private Integer time;
    private Long id;

    @BeforeEach
    public void setup() {
        time = 1;
        id = 1L;

        prontuarioDTO = new ProntuarioDTO(01L,"historico", "receituario", "exames");
        prontuario = new Prontuario(id, prontuarioDTO.getRegistroAgenda(), prontuarioDTO.getHistorico(), prontuarioDTO.getReceituario(), prontuarioDTO.getExames());
        consulta = new Consulta();
        consulta.setId(id);
    }

    /**
     * Teste do método cadastrarProntuario
     * @throws Exception
     */
    @Test
    public void cadastrarProntuarioTest() throws Exception {
        
        when(prontuarioMapper.toEntity(prontuarioDTO)).thenReturn(prontuario);
        when(prontuarioRepository.save(prontuario)).thenReturn(prontuario);
        when(agendamentoClient.buscarConsulta()).thenReturn(consulta);

        prontuarioService.cadastrarProntuario(prontuarioDTO);

        verify(prontuarioRepository,times(time)).save(prontuario);
        verify(prontuarioMapper,times(time)).toEntity(prontuarioDTO);
    }

    /**
     * Teste do método buscarTodosProntuarios
     * @throws Exception
     */
    @Test
    public void buscarTodosProntuariosTest() throws Exception {

        List<Prontuario> prontuarios = new ArrayList<>();
        prontuarios.add(prontuario);

        List<ProntuarioDTO> prontuarioDTOs = new ArrayList<>();
        prontuarioDTOs.add(prontuarioDTO);

        when(prontuarioRepository.findAll()).thenReturn(prontuarios);
        when(prontuarioMapper.toDTOList(prontuarios)).thenReturn(prontuarioDTOs);
        
        List<ProntuarioDTO> expectativeProntuarios = prontuarioService.buscarTodosProntuarios();

        assert(expectativeProntuarios).equals(prontuarioDTOs);

        verify(prontuarioRepository,times(time)).findAll();
        verify(prontuarioMapper,times(time)).toDTOList(prontuarioRepository.findAll());
    }

    /**
     * Teste do método visualizarProntuario
     * @throws Exception
     */
    @Test
    public void visualizarProntuarioTest() throws Exception {   

        when(prontuarioRepository.existsById(id)).thenReturn(true);
        when(prontuarioRepository.findById(id)).thenReturn(Optional.of(prontuario));
        when(prontuarioMapper.toDTO(prontuario)).thenReturn(prontuarioDTO);

        ProntuarioDTO expectativeProntuario = prontuarioService.visualizarProntuario(id);

        assert(expectativeProntuario).equals(prontuarioDTO);

        verify(prontuarioRepository,times(time)).findById(id);
        verify(prontuarioMapper,times(time)).toDTO(prontuario);
    }

    /**
     * Teste do método editarProntuario
     * @throws Exception
     */
    @Test
    public void editarProntuarioTest() throws Exception {

        when(prontuarioRepository.existsById(id)).thenReturn(true);
        when(agendamentoClient.buscarConsulta()).thenReturn(consulta);
        when(prontuarioMapper.toEntity(prontuarioDTO, id)).thenReturn(prontuario);
        when(prontuarioRepository.save(prontuario)).thenReturn(prontuario);

        prontuarioService.editarProntuario(id, prontuarioDTO);

        verify(prontuarioRepository,times(time)).save(prontuario);
        verify(prontuarioMapper,times(time)).toEntity(prontuarioDTO, id);
    }

    /**
     * Teste do método excluirProntuario
     * @throws Exception
     */
    @Test
    public void excluirProntuarioTest() throws Exception {

        when(prontuarioRepository.existsById(id)).thenReturn(true);
        when(prontuarioRepository.findById(id)).thenReturn(Optional.of(prontuario));

        prontuarioService.excluirProntuario(id);

        verify(prontuarioRepository,times(time)).delete(prontuario);
    }

    /**
     * Teste do método buscarConsulta
     * @throws Exception
     */
    @Test
    public void buscarConsultaTest() throws Exception {
        when(agendamentoClient.buscarConsulta()).thenReturn(consulta);

        Consulta expectativeConsulta = prontuarioService.buscarConsulta();

        assert(expectativeConsulta).equals(consulta);
        verify(agendamentoClient,times(time)).buscarConsulta();
    }
}
