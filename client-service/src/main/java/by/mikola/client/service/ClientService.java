package by.mikola.client.service;

import by.mikola.client.dto.ClientDTO;
import by.mikola.client.entity.Client;

import java.util.List;

public interface ClientService {
    List<Client> getClients();

    Client getClientById(Long clientId);

    Client getClientByEmail(String email);

    Client createClient(ClientDTO clientDTO);

    void deleteClient(Long clientId);

}
