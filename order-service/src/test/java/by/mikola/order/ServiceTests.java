package by.mikola.order;

import by.mikola.order.dto.order.OrderUpdateRequest;
import by.mikola.order.entity.enums.OrderStatus;
import by.mikola.order.entity.order.Order;
import by.mikola.order.mapper.OrderMapper;
import by.mikola.order.repository.OrderRepository;
import by.mikola.order.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ServiceTests.class)
class ServiceTests {

    @Mock
    OrderRepository repository;

    @Mock
    OrderMapper mapper;

    @InjectMocks
    OrderServiceImpl service;

    @Test
    void testGetAllOrders() {
        List<Order> orders = Arrays.asList(Order.builder().id(1L).description("First order").status(OrderStatus.OPENED).clientId("1").build(), Order.builder().id(2L).description("Second order").status(OrderStatus.DELIVERED).clientId("2").build());

        when(repository.findAll()).thenReturn(orders);

        assertEquals(2, service.getOrders().size());
    }

    @Test
    void testGetOrderById() {
        Long id = 1L;
        Optional<Order> order = Optional.of(Order.builder()
                .id(id)
                .description("First order")
                .status(OrderStatus.OPENED)
                .clientId("1")
                .build());

        when(repository.findById(id)).thenReturn(order);

        assertEquals(id, service.getOrderById(id).getId());
    }

    @Test
    void testUpdateOrder() {
        Long id = 1L;
        String updatedDescription = "Updated description";
        OrderStatus updatedStatus = OrderStatus.DELIVERED;
        OrderUpdateRequest updateRequest = OrderUpdateRequest.builder()
                .description(updatedDescription)
                .status(updatedStatus)
                .build();
        Optional<Order> order = Optional.of(Order.builder()
                .id(1L)
                .description(updatedDescription)
                .status(updatedStatus)
                .clientId("1")
                .build());
        Order updatedOrder = Order.builder()
                .id(1L)
                .description(updatedDescription)
                .status(updatedStatus)
                .clientId("1")
                .build();

        when(repository.findById(id)).thenReturn(order);
        when(repository.save(updatedOrder)).thenReturn(updatedOrder);

        assertEquals(updatedOrder, service.updateOrder(id, updateRequest));
    }

}
