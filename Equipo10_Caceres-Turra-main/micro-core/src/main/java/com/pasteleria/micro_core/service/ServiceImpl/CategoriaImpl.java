package com.pasteleria.micro_core.service.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pasteleria.micro_core.dto.CategoriaDTO;
import com.pasteleria.micro_core.entity.Categoria;
import com.pasteleria.micro_core.repository.CategoriaRepository;
import com.pasteleria.micro_core.service.CategoriaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaImpl implements CategoriaService{

    private final CategoriaRepository categoriaRepo;
    
    @Override
    public List<CategoriaDTO> listarCategorias() {
        return categoriaRepo.findAll().stream()
        .map(this::convertirDto)
        .collect(Collectors.toList());
    }

    @Override
    public CategoriaDTO obtenerCategoriaPorId(Long id) {
        return categoriaRepo.findById(id).map(this::convertirDto)
        .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
    }

    @Override
    public CategoriaDTO guardarCategoria(CategoriaDTO dto) {
        Categoria categoria = Categoria.builder()
        .nombre(dto.getNombre())
        .descripcion(dto.getDescripcion())
        .build();
        return convertirDto(categoriaRepo.save(categoria));
    }

    @Override
    public CategoriaDTO actualizarCategoria(Long id, CategoriaDTO dto) {
        Categoria categoria = categoriaRepo.findById(id)
        .orElseThrow(()-> new RuntimeException("Categoria no encontrada"));
        categoria.setNombre(dto.getNombre());
        categoria.setDescripcion(dto.getDescripcion());
        return convertirDto(categoriaRepo.save(categoria));
    }

    @Override
    public void eliminarCategoria(Long id) {
        categoriaRepo.deleteById(id);
    }

    private CategoriaDTO convertirDto(Categoria c){
        return CategoriaDTO.builder()
        .id(c.getId())
        .nombre(c.getNombre())
        .descripcion(c.getDescripcion())
        .build();
    }

}
