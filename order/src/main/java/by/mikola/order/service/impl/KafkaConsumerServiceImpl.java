package by.mikola.order.service.impl;

import by.mikola.order.config.KafkaConsumerConfig;
import by.mikola.order.dto.order.OrderCreateRequest;
import by.mikola.order.service.KafkaConsumerService;
import by.mikola.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumerServiceImpl implements KafkaConsumerService{

    private static final Logger logger
            = LoggerFactory.getLogger(KafkaConsumerServiceImpl.class);

    private final OrderService orderService;

    @KafkaListener(topics = KafkaConsumerConfig.TOPIC, groupId = "orderGroup")
    public void listenOrder(OrderCreateRequest createRequest) {
        logger.info("Received order {} from topic: {}", createRequest, KafkaConsumerConfig.TOPIC);
        orderService.saveOrder(createRequest);
    }
}

