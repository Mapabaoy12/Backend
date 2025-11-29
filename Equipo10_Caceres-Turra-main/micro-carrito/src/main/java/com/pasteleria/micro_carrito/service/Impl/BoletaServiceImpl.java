package com.pasteleria.micro_carrito.service.Impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import com.pasteleria.micro_carrito.entity.Boleta;
import com.pasteleria.micro_carrito.entity.Carrito;
import com.pasteleria.micro_carrito.entity.DetalleBoleta;
import com.pasteleria.micro_carrito.repository.BoletaRepository;
import com.pasteleria.micro_carrito.repository.CarritoRepository;
import com.pasteleria.micro_carrito.service.BoletaService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoletaServiceImpl implements BoletaService{
    private final CarritoRepository carritoRepo;
    private final BoletaRepository boletaRepo;
    
    @Override
    @Transactional
    public Boleta generarBoleta(Long carritoId){
        Carrito carrito = carritoRepo.findById(carritoId)
            .orElseThrow(() -> new RuntimeException("Carrito no encontrado")) ;

            if (carrito.getItems().isEmpty()) {
                throw new RuntimeException("No se puede generar boleta con un carrito vacio");
            }

            Boleta boleta = Boleta.builder()
                .usuarioId(carrito.getUsuarioId())
                .fecha(LocalDateTime.now())
                .total(carrito.getTotal())
                .detalles(new ArrayList<>())
                .build();

            List<DetalleBoleta> detalles = carrito.getItems().stream().map(item -> {
                return DetalleBoleta.builder()
                        .productoId(item.getProductoId())
                        .cantidad(item.getCantidad())
                        .precioUnitario(item.getPrecioUnitario())
                        .subtotal(item.getSubtotal())
                        .boleta(boleta)
                        .build();

            }).collect(Collectors.toList());
            
            boleta.setDetalles(detalles);

            Boleta boletaGuardada = boletaRepo.save(boleta);

            carrito.getItems().clear();
            carrito.setTotal(0.0);
            carritoRepo.save(carrito);

            return boletaGuardada;

    }
}
