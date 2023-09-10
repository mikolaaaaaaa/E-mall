package by.mikola.client.controller.impl;

import by.mikola.client.controller.ClientController;
import by.mikola.client.dto.ClientDTO;
import by.mikola.client.entity.Client;
import by.mikola.client.mapper.ClientMapper;
import by.mikola.client.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ClientControllerImpl implements ClientController {
    private final ClientService clientService;
    private final ClientMapper mapper;

    @Override
    public List<ClientDTO> getAllClients() {
        return clientService.getClients()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public ClientDTO getClientById(Long id) {
        return mapper.toDto(clientService.getClientById(id));
    }

    @Override
    public ClientDTO findByEmail(String email) {
        log.info("Start find client by email {}", email);
        Client client = clientService.getClientByEmail(email);
        log.info("Success find client by email {}", email);
        return mapper.toDto(client);
    }

    @Override
    public ClientDTO createClient(ClientDTO clientDTO) {
        return mapper.toDto(clientService.createClient(clientDTO));
    }

    @Override
    public void deleteClient(Long id) {
        clientService.deleteClient(id);
    }
}
