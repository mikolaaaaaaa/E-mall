package by.mikola.order.feign;

import by.mikola.order.dto.client.ClientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "http://localhost:8081/client/api/v1/clients", name = "clientFeign")
public interface ClientFeign {

    @GetMapping("/{id}")
    ClientDTO getClientById(@PathVariable("id") String id);

    @GetMapping("/findByEmail/{email}")
    ClientDTO getClientByEmail(@PathVariable("email") String email);

    @PostMapping
    ClientDTO createClient(@RequestBody ClientDTO createRequest);

}
