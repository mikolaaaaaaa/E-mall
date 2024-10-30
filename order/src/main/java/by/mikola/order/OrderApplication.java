package by.mikola.order;

import by.mikola.order.configprops.KafkaConfigProperties;
import by.mikola.order.configprops.KafkaConsumerProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaServer
@EnableMongoRepositories(basePackages = "by.mikola.order.repository")
@EnableConfigurationProperties({KafkaConfigProperties.class,
        KafkaConsumerProperties.class})
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

}
