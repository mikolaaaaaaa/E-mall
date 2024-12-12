package by.mikola.order.service.impl;

import by.mikola.order.dto.order.OrderCreateRequest;
import by.mikola.order.entity.Order;
import by.mikola.order.entity.enums.OrderStatus;
import by.mikola.order.exception.NotFoundException;
import by.mikola.order.repository.OrderRepository;
import by.mikola.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(String orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Cannot find order with id %s".formatted(orderId)));
    }

    @Override
    public Order saveOrder(OrderCreateRequest createRequest) {
        Order order = Order.builder()
                .status(OrderStatus.OPENED)
                .description(createRequest.getDescription())
                .clientId(createRequest.getClient().getId())
                .build();
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(String orderId) {
        orderRepository.deleteById(orderId);
    }
}
