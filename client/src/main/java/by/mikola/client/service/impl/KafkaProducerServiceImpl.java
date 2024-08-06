package by.mikola.client.service.impl;

import by.mikola.client.config.KafkaTopicConfig;
import by.mikola.client.dto.OrderCreateRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class KafkaProducerServiceImpl {

    private final KafkaTemplate<String, OrderCreateRequest> kafkaTemplate;

    private static final Logger logger
            = LoggerFactory.getLogger(KafkaProducerServiceImpl.class);

    public void sendMessage(OrderCreateRequest orderCreateRequest) {
        CompletableFuture<SendResult<String, OrderCreateRequest>> future = kafkaTemplate.send(KafkaTopicConfig.ORDER_TOPIC, orderCreateRequest);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                logger.info("Order {} was sent", result);
            } else {
                logger.info("Order {} wasn't sent", ex.getMessage());
            }
        });
    }
}
