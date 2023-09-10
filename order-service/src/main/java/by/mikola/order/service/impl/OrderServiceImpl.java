package by.mikola.order.service.impl;

import by.mikola.order.dto.client.ClientDTO;
import by.mikola.order.dto.order.OrderCreateRequest;
import by.mikola.order.dto.order.OrderDTO;
import by.mikola.order.dto.order.OrderUpdateRequest;
import by.mikola.order.entity.enums.OrderStatus;
import by.mikola.order.entity.order.Order;
import by.mikola.order.exception.ConflictException;
import by.mikola.order.exception.NotFoundException;
import by.mikola.order.feign.ClientFeign;
import by.mikola.order.mapper.OrderMapper;
import by.mikola.order.repository.OrderRepository;
import by.mikola.order.service.OrderService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final ClientFeign clientFeign;

    @Override
    public List<Order> getOrders() {
        return repository.findAll();
    }

    @Override
    public Order getOrderById(Long orderId) {
        return repository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order with id %s not found".formatted(orderId)));
    }

    @Override
    public Order saveOrder(OrderCreateRequest createRequest) {
        ClientDTO client = createRequest.getClient();
        if (client.getId() != null) {
            try {
                clientFeign.getClientById(client.getId());
            } catch (FeignException.NotFound e) {
                throw new ConflictException("Client with id %s does not exists".formatted(client.getId()));
            }
        } else if (client.getEmail() != null) {
            try {
                client = clientFeign.getClientByEmail(client.getEmail());
            } catch (FeignException.NotFound e) {
                client = clientFeign.createClient(client);
            }
        }

        Order order = Order.builder()
                .status(OrderStatus.OPENED)
                .description(createRequest.getDescription())
                .clientId(client.getId())
                .build();

        return repository.save(order);
    }

    @Override
    public Order updateOrder(Long id, OrderUpdateRequest updateRequest) {
        Order order = getOrderById(id);

        OrderDTO orderDto = mapper.toDto(updateRequest);
        mapper.update(order, orderDto);
        return repository.save(order);
    }

    @Override
    public void deleteOrder(Long orderId) {
        if (!repository.existsById(orderId)) {
            throw new NotFoundException("Order does not exists");
        }
        repository.deleteById(orderId);
    }
}
