package by.mikola.client.config;

import by.mikola.client.configprops.KafkaConfigProperties;
import by.mikola.client.dto.OrderCreateRequest;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaProducerConfig {

    private final KafkaConfigProperties kafkaConfigProperties;

    @Bean
    public ProducerFactory<String, OrderCreateRequest> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                kafkaConfigProperties.getBootstrapServers());
        configProps.put(
                ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG,
                true
        );
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);
        var producerFactory = new DefaultKafkaProducerFactory<String, OrderCreateRequest>(configProps,
                        new StringSerializer(),
                        new JsonSerializer<>());
        producerFactory.setTransactionIdPrefix("emall-");
        return producerFactory;
    }

    @Bean
    public KafkaTemplate<String, OrderCreateRequest> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
