package by.mikola.order.service;

import by.mikola.order.dto.order.OrderCreateRequest;
import by.mikola.order.dto.order.OrderUpdateRequest;
import by.mikola.order.entity.order.Order;

import java.util.List;

public interface OrderService {
    List<Order> getOrders();

    Order getOrderById(Long orderId);

    Order saveOrder(OrderCreateRequest createRequest);

    Order updateOrder(Long id, OrderUpdateRequest updateRequest);

    void deleteOrder(Long orderId);
}
