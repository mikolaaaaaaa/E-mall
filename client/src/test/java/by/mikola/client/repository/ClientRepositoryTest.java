package by.mikola.client.repository;

import by.mikola.client.entity.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ClientRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");

    @Autowired
    ClientRepository clientRepository;

    @Test
    void connectionEstablished() {

        assertThat(postgres.isCreated()).isTrue();
        assertThat(postgres.isRunning()).isTrue();

    }

    @BeforeEach
    void setUp() {
        var clients = List.of(new Client(1L, "test1@test.test"),
                new Client(2L, "test2@test.test"),
                new Client(3L, "test3@test.test"));
        clientRepository.saveAll(clients);
    }

    @Test
    void shouldReturnClientByEmail() {
        var client = clientRepository.findByEmail("test1@test.test");
        assertThat(client).isNotNull();
    }
}
