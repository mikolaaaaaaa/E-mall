package by.mikola.order.controller;


import by.mikola.order.dto.order.OrderCreateRequest;
import by.mikola.order.dto.order.OrderDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@RequestMapping("/v1/orders")
public interface OrderController {

    @GetMapping
    List<OrderDTO> getAllOrders();

    @GetMapping("/{id}")
    OrderDTO getOrderById(@PathVariable String id);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    OrderDTO createOrder(@Valid @RequestBody OrderCreateRequest createRequest);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteOrder(@PathVariable String id);
}
