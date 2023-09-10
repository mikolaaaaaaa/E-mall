package by.mikola.client.controller;

import by.mikola.client.dto.ClientDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/clients")
public interface ClientController {

    @GetMapping
    List<ClientDTO> getAllClients();

    @GetMapping("/{id}")
    ClientDTO getClientById(@PathVariable Long id);

    @GetMapping("/findByEmail/{email}")
    ClientDTO findByEmail(@PathVariable String email);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ClientDTO createClient(@Valid @RequestBody ClientDTO clientDTO);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteClient(@PathVariable Long id);
}
