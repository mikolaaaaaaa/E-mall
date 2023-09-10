package by.mikola.order;

import by.mikola.order.dto.order.OrderUpdateRequest;
import by.mikola.order.entity.enums.OrderStatus;
import by.mikola.order.entity.order.Order;
import by.mikola.order.mapper.OrderMapper;
import by.mikola.order.repository.OrderRepository;
import by.mikola.order.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    OrderRepository repository;

    @Mock
    OrderMapper mapper;

    @InjectMocks
    OrderServiceImpl service;

    @Test
    @DisplayName("Happy Path Test: get all orders use cases")
    void testGetAllOrders() {
        // given
        List<Order> orders = Arrays.asList(Order.builder().id(1L).description("First order").status(OrderStatus.OPENED).clientId("1").build(), Order.builder().id(2L).description("Second order").status(OrderStatus.DELIVERED).clientId("2").build());

        //when
        when(repository.findAll()).thenReturn(orders);

        //then
        assertEquals(2, service.getOrders().size());
    }

    @Test
    @DisplayName("Happy Path Test: get order by id use cases")
    void testGetOrderById() {
        //giver
        Long id = 1L;
        Optional<Order> order = Optional.of(Order.builder()
                .id(id)
                .description("First order")
                .status(OrderStatus.OPENED)
                .clientId("1")
                .build());

        //when
        when(repository.findById(id)).thenReturn(order);

        //then
        assertEquals(id, service.getOrderById(id).getId());
    }

    @Test
    @DisplayName("Happy Path Test: get all orders use cases")
    void testUpdateOrder() {
        //given
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

        //when
        when(repository.findById(id)).thenReturn(order);
        when(repository.save(updatedOrder)).thenReturn(updatedOrder);

        //then
        assertEquals(updatedOrder, service.updateOrder(id, updateRequest));
    }

}
