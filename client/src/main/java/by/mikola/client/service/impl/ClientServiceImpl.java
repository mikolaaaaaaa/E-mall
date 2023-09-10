package by.mikola.client.service.impl;

import by.mikola.client.dto.ClientDTO;
import by.mikola.client.entity.Client;
import by.mikola.client.exception.NotFoundException;
import by.mikola.client.mapper.ClientMapper;
import by.mikola.client.repository.ClientRepository;
import by.mikola.client.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;
    private final ClientMapper mapper;

    @Override
    public List<Client> getClients() {
        return repository.findAll();
    }

    @Override
    public Client getClientById(Long clientId) {
        return repository.findById(clientId)
                .orElseThrow(() -> new NotFoundException("Client with id %s not found".formatted(clientId)));
    }

    @Override
    public Client getClientByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Client with email %s not found".formatted(email)));
    }

    @Override
    public Client createClient(ClientDTO client) {
        return repository.save(mapper.toEntity(client));
    }

    @Override
    public void deleteClient(Long clientId) {
        if (!repository.existsById(clientId)) {
            throw new NotFoundException("Client with id %s not found".formatted(clientId));
        }
        repository.deleteById(clientId);
    }
}
