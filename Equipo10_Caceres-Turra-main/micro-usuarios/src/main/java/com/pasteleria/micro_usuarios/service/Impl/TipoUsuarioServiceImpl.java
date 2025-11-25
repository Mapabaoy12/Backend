package com.pasteleria.micro_usuarios.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pasteleria.micro_usuarios.dto.TipoUsuarioDTO;
import com.pasteleria.micro_usuarios.entity.TipoUsuarioEntity;
import com.pasteleria.micro_usuarios.repository.TipoUsuarioRepository;
import com.pasteleria.micro_usuarios.service.TipoUsuarioService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TipoUsuarioServiceImpl implements TipoUsuarioService{

    private final TipoUsuarioRepository tipoUsuarioRepo;

    @Override
    public List<TipoUsuarioDTO> listarTipos() {
        return tipoUsuarioRepo.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public TipoUsuarioDTO buscarPorId(Long id) {
        return tipoUsuarioRepo.findById(id)
                .map(this::convertirADTO)
                .orElseThrow(() -> new RuntimeException("Tipo de usuario no encontrado"));
    }

    @Override
    public TipoUsuarioDTO crear(TipoUsuarioDTO tipoUsuarioDTO) {
        TipoUsuarioEntity tipo = aEntidad(tipoUsuarioDTO);
        return convertirADTO(tipoUsuarioRepo.save(tipo));
    }

    @Override
    public TipoUsuarioDTO actualizar(Long id, TipoUsuarioDTO tipoUsuarioDTO) {
        TipoUsuarioEntity existente = tipoUsuarioRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de usuario no encontrado"));
        existente.setNombre(tipoUsuarioDTO.getNombre());
        return convertirADTO(tipoUsuarioRepo.save(existente));
    }

    @Override
    public void eliminar(Long id) {
        tipoUsuarioRepo.deleteById(id);
    }

    private TipoUsuarioDTO convertirADTO(TipoUsuarioEntity usr) {
        return TipoUsuarioDTO.builder()
                .id(usr.getId())
                .nombre(usr.getNombre())
                .descripcion(usr.getDescripcion())
                .build();
    }

    private TipoUsuarioEntity aEntidad(TipoUsuarioDTO dto) {
        return TipoUsuarioEntity.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .build();
    }

}
