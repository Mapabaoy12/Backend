package com.pasteleria.micro_usuarios.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pasteleria.micro_usuarios.dto.UsuarioDto;
import com.pasteleria.micro_usuarios.entity.TipoUsuarioEntity;
import com.pasteleria.micro_usuarios.entity.UsuarioEntity;
import com.pasteleria.micro_usuarios.repository.TipoUsuarioRepository;
import com.pasteleria.micro_usuarios.repository.UsuarioRepository;
import com.pasteleria.micro_usuarios.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioRepository usuarioRepo;
    private final TipoUsuarioRepository tipoUsuarioRepo;

    @Override
    public List<UsuarioDto> listarUsuarios() {
        return usuarioRepo.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDto obtenerPorID(long id) {
        return usuarioRepo.findById(id)
                .map(this::convertirADTO)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

    }

    @Override
    public UsuarioDto crearUsuario(UsuarioDto usuarioDto) {
        TipoUsuarioEntity tipo = tipoUsuarioRepo.findByNombre(usuarioDto.getTipoUsuario());
        UsuarioEntity usuario = UsuarioEntity.builder()
                .nombre(usuarioDto.getNombre())
                .apellido(usuarioDto.getApellido())
                .email(usuarioDto.getEmail())
                .contrasenia("hola") // temporal
                .tipoUsuario(tipo)
                .build();
        return convertirADTO(usuarioRepo.save(usuario));
    }

    @Override
    public UsuarioDto actualizarUsuario(long id, UsuarioDto usuarioDto) {
        UsuarioEntity usuario = usuarioRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Usuario no encontrado"));
                usuario.setNombre(usuarioDto.getNombre());
                usuario.setApellido(usuarioDto.getApellido());
                usuario.setEmail(usuarioDto.getEmail());
                usuario.setTipoUsuario(tipoUsuarioRepo.findByNombre(usuarioDto.getTipoUsuario()));
                return convertirADTO(usuarioRepo.save(usuario));
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepo.deleteById(id);
    }

    private UsuarioDto convertirADTO(UsuarioEntity usuario) {
        return UsuarioDto.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .email(usuario.getEmail())
                .tipoUsuario(usuario.getTipoUsuario().getNombre())
                .build();
    }

}
