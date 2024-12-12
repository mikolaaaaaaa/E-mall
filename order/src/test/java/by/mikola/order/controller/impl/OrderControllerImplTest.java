package by.mikola.order.controller.impl;

import by.mikola.order.entity.Order;
import by.mikola.order.entity.enums.OrderStatus;
import by.mikola.order.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.kafka.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderControllerImplTest {

    @Container
    @ServiceConnection
    static MongoDBContainer mongo = new MongoDBContainer("mongo:latest");

    @Container
    static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("apache/kafka").asCompatibleSubstituteFor("confluentinc/cp-kafka"));

    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
    }
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        var orders = List.of(new Order("66c3adfd7b02d84e8c384866", "1", "test1", OrderStatus.OPENED),
                new Order("66d884127b55720460f0101d", "2", "test2", OrderStatus.OPENED),
                new Order("66f17b2da33fb6481298f208", "3", "test3", OrderStatus.OPENED));
        orderRepository.saveAll(orders);
    }

    @Test
    void shouldFindAllOrders() {
        var orders = restTemplate.getForObject("/v1/orders", String.class);
        assertThat(orders).isNotEmpty();
    }

    @Test
    void shouldFindClientWhenValidClientId() {
        ResponseEntity<Order> response = restTemplate.exchange("/v1/orders/66c3adfd7b02d84e8c384866", HttpMethod.GET, null, Order.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    void shouldThrowNotFoundWhenInvalidClientId() {
        ResponseEntity<String> response = restTemplate.exchange("/v1/orders/66c3adfd7b02d84e8c384863", HttpMethod.GET, null, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}