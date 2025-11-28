package com.pasteleria.micro_carrito.service.Impl;

import org.springframework.stereotype.Service;
import com.azure.messaging.servicebus.*;
import com.fasterxml.jackson.databind.ObjectMapper; 
import com.azure.messaging.servicebus.*;
import com.fasterxml.jackson.databind.ObjectMapper; 
import com.pasteleria.micro_carrito.dto.CarritoDTO;
import com.pasteleria.micro_carrito.dto.ItemDTO;
import com.pasteleria.micro_carrito.entity.Carrito;
import com.pasteleria.micro_carrito.entity.Item;
import com.pasteleria.micro_carrito.repository.CarritoRepository;
import com.pasteleria.micro_carrito.repository.ItemRepository;
import com.pasteleria.micro_carrito.service.CarritoService;

import lombok.RequiredArgsConstructor;
/*ola */
@Service
@RequiredArgsConstructor
public class CarritoImpl implements CarritoService {

        private final CarritoRepository carritoRepo;
        private final ItemRepository itemRepo;

        private void enviarMensajeACola(CarritoDTO carrito) {
                String connectionString = System.getenv("AZURE_SB_CONNECTION_STRING");
                String queueName = "colapedidos";

                try {
                String mensajeJson = new ObjectMapper().writeValueAsString(carrito);

                ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
                        .connectionString(connectionString)
                        .sender()
                        .queueName(queueName)
                        .buildClient();

                senderClient.sendMessage(new ServiceBusMessage(mensajeJson));
                System.out.println("Mensaje enviado a la cola Azure: " + carrito.getId());

                senderClient.close();
                } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error enviando a la cola: " + e.getMessage());
                }
        }

        @Override
        public CarritoDTO crearCarrito(Long usuarioId) {
                Carrito carrito = Carrito.builder()
                        .usuarioId(usuarioId)
                        .total(0.0)
                        .build();
                carritoRepo.save(carrito);
                return mapToDTO(carrito);
        }

        @Override
        public CarritoDTO agregarItem(Long carritoId, ItemDTO itemDTO) {
                Carrito carrito = carritoRepo.findById(carritoId)
                        .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

                Item item = Item.builder()
                        .productoId(itemDTO.getProductoId())
                        .cantidad(itemDTO.getCantidad())
                        .precioUnitario(itemDTO.getPrecioUnitario())
                        .carrito(carrito)
                        .build();

                itemRepo.save(item);
                carrito.getItems().add(item);
                carrito.calcularTotal();
                carritoRepo.save(carrito);

                CarritoDTO dtoRespuesta = mapToDTO(carrito);

                enviarMensajeACola(dtoRespuesta);

                return dtoRespuesta;
        }

        @Override
        public CarritoDTO obtenerCarrito(Long carritoId) {
                return carritoRepo.findById(carritoId)
                        .map(this::mapToDTO)
                        .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        }

        @Override
        public void eliminarItem(Long carritoId, Long itemId) {
                itemRepo.deleteById(itemId);
                Carrito carrito = carritoRepo.findById(carritoId)
                        .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
                carrito.calcularTotal();
                carritoRepo.save(carrito);
        }

        private CarritoDTO mapToDTO(Carrito carrito) {
                return CarritoDTO.builder()
                        .id(carrito.getId())
                        .usuarioId(carrito.getUsuarioId())
                        .items(carrito.getItems().stream()
                                .map(i -> ItemDTO.builder()
                                        .productoId(i.getProductoId())
                                        .cantidad(i.getCantidad())
                                        .precioUnitario(i.getPrecioUnitario())
                                        .subtotal(i.getSubtotal())
                                        .build())
                                .toList())
                        .total(carrito.getTotal())
                        .build();
        }



}
