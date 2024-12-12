package by.mikola.order.service;

import by.mikola.order.dto.order.OrderCreateRequest;

@SuppressWarnings("unused")
public interface KafkaConsumerService {
    void listenOrder(OrderCreateRequest createRequest);
}

