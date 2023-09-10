package by.mikola.order.controller;


import by.mikola.order.dto.order.OrderCreateRequest;
import by.mikola.order.dto.order.OrderDTO;
import by.mikola.order.dto.order.OrderUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/orders")
public interface OrderController {

    @GetMapping
    List<OrderDTO> getAllOrders();

    @GetMapping("/{id}")
    OrderDTO getOrderById(@PathVariable Long id);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    OrderDTO createOrder(@Valid @RequestBody OrderCreateRequest createRequest);

    @PatchMapping("/{orderId}")
    OrderDTO updateOrder(@PathVariable Long orderId,
                         @Validated @RequestBody OrderUpdateRequest orderDto);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteOrder(@PathVariable Long id);
}
