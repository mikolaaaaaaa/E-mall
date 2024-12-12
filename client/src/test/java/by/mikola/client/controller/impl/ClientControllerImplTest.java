package by.mikola.client.controller.impl;

import by.mikola.client.entity.Client;
import by.mikola.client.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClientControllerImplTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    ClientRepository clientRepository;

    @BeforeEach
    void setUp() {
        var clients = List.of(new Client(1L, "test1@test.test"),
                new Client(2L, "test2@test.test"),
                new Client(3L, "test3@test.test"));
        clientRepository.saveAll(clients);
    }

    @Test
    void shouldFindAllClients() {
        var clients = restTemplate.getForObject("/v1/clients", String.class);
        assertThat(clients).isNotEmpty();
    }

    @Test
    void shouldFindClientWhenValidClientId() {
        ResponseEntity<Client> response = restTemplate.exchange("/v1/clients/1", HttpMethod.GET, null, Client.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    void shouldThrowNotFoundWhenInvalidClientId() {
        ResponseEntity<Client> response = restTemplate.exchange("/v1/clients/10", HttpMethod.GET, null, Client.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}