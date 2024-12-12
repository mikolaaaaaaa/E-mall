package by.mikola.order.configprops;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.kafka.consumer.properties.isolation")
public class KafkaConsumerProperties {
        private String level;
}
