package by.mikola.client.controller.impl;

import by.mikola.client.dto.OrderCreateRequest;
import by.mikola.client.service.impl.KafkaProducerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kafka")
public class KafkaTestController {

    @Autowired
    private KafkaProducerServiceImpl kafkaProducerService;

    @PostMapping("/publish")
    public void sendMessageToKafkaTopic(@RequestBody OrderCreateRequest orderCreateRequest) {
        kafkaProducerService.sendMessage(orderCreateRequest);
    }
}
