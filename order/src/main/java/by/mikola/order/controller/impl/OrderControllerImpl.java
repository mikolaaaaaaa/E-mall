package by.mikola.order.controller.impl;

import by.mikola.order.controller.OrderController;
import by.mikola.order.dto.order.OrderCreateRequest;
import by.mikola.order.dto.order.OrderDTO;
import by.mikola.order.mapper.OrderMapper;
import by.mikola.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderControllerImpl implements OrderController {
    private final OrderService orderService;
    private final OrderMapper mapper;

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderService.getOrders()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public OrderDTO getOrderById(String id) {
        return mapper.toDto(orderService.getOrderById(id));
    }

    @Override
    public OrderDTO createOrder(OrderCreateRequest createRequest) {
        return mapper.toDto(orderService.saveOrder(createRequest));
    }

    @Override
    public void deleteOrder(String id) {
        orderService.deleteOrder(id);
    }
}
