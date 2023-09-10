package by.mikola.order.dto.order;

import by.mikola.order.dto.client.ClientDTO;
import by.mikola.order.validator.ValidClient;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ValidClient
public class OrderCreateRequest {

    ClientDTO client;

    @Size(max = 150)
    private String description = "";
}
