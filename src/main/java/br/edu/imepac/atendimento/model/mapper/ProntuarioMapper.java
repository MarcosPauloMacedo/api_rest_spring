package br.edu.imepac.atendimento.model.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

import br.edu.imepac.atendimento.model.dto.ProntuarioDTO;
import br.edu.imepac.atendimento.model.entity.Prontuario;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProntuarioMapper {

    private final ModelMapper modelMapper;

    public Prontuario toEntity(ProntuarioDTO prontuarioDTO, Long id) {
        Prontuario prontuario = mapProntuario(prontuarioDTO);
        prontuario.setId(id);

        return prontuario;
    }

    public Prontuario toEntity(ProntuarioDTO prontuarioDTO) {
        Prontuario prontuario = mapProntuario(prontuarioDTO);
        return prontuario;
    }

    public ProntuarioDTO toDTO(Prontuario prontuario) {
        ProntuarioDTO prontuarioDTO = modelMapper.map(prontuario, ProntuarioDTO.class);

        return prontuarioDTO;
    }

    public List<ProntuarioDTO> toDTOList(List<Prontuario> prontuarios) {
        List<ProntuarioDTO> prontuarioDTOs = prontuarios.stream()
                .map(prontuario -> modelMapper.map(prontuario, ProntuarioDTO.class))
                .collect(Collectors.toList());

        return prontuarioDTOs;
    }

    private Prontuario mapProntuario(ProntuarioDTO prontuarioDTO) {
        Prontuario prontuario = modelMapper.map(prontuarioDTO, Prontuario.class);

        return prontuario;
    }
}
