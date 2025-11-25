package com.pasteleria.micro_core.service.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pasteleria.micro_core.dto.ProductoDTO;
import com.pasteleria.micro_core.entity.Categoria;
import com.pasteleria.micro_core.entity.Producto;
import com.pasteleria.micro_core.repository.CategoriaRepository;
import com.pasteleria.micro_core.repository.ProductoRepository;
import com.pasteleria.micro_core.service.ProductoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoImpl implements ProductoService {

    private final ProductoRepository productoRepo;
    private final CategoriaRepository categoriaRepo;

    @Override
    public List<ProductoDTO> listarProductos() {
        return productoRepo.findAll()
        .stream()
        .map(this::convertirDto)
        .collect(Collectors.toList());
    }

    @Override
    public ProductoDTO obtenerPorId(Long id) {
        return productoRepo.findById(id).map(this::convertirDto)
        .orElseThrow(()-> new RuntimeException("Producto no encontrado"));
    }

    @Override
    public ProductoDTO guardarProducto(ProductoDTO DTO) {
        Categoria categoria = categoriaRepo.findByNombre(DTO.getCategoria());
        Producto producto = Producto.builder()
                .nombre(DTO.getNombre())
                .descripcion(DTO.getDescripcion())
                .precio(DTO.getPrecio())
                .cantidad(DTO.getCantidad())
                .categoria(categoria)
                .build();
        return convertirDto(productoRepo.save(producto));
    }

    @Override
    public ProductoDTO actualizarProducto(Long id, ProductoDTO productoDTO) {
        Producto producto = productoRepo.findById(id).orElseThrow(()-> new RuntimeException("Producto no encontrado"));
        Categoria categoria = categoriaRepo.findByNombre(productoDTO.getCategoria());
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setCantidad(productoDTO.getCantidad());
        producto.setCategoria(categoria);
        return convertirDto(productoRepo.save(producto));
    }

    @Override
    public void eliminarProducto(Long id) {
        productoRepo.deleteById(id);
    }

    @Override
    public List<ProductoDTO> obtenerProductosPorCategoria(String nombreCate) {
        return productoRepo.findByCategoriaNombre(nombreCate)
                .stream()
                .map(this::convertirDto)
                .collect(Collectors.toList());
    }


    private ProductoDTO convertirDto(Producto p){
        return ProductoDTO.builder()
        .id(p.getId())
        .nombre(p.getNombre())
        .descripcion(p.getDescripcion())
        .precio(p.getPrecio())
        .cantidad(p.getCantidad())
        .categoria(p.getCategoria().getNombre())
        .build();
    }

}
