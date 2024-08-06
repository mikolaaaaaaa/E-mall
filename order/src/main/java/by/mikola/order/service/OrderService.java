package by.mikola.order.service;

import by.mikola.order.dto.order.OrderCreateRequest;
import by.mikola.order.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> getOrders();

    Order getOrderById(String orderId);

    Order saveOrder(OrderCreateRequest createRequest);

    void deleteOrder(String orderId);
}
