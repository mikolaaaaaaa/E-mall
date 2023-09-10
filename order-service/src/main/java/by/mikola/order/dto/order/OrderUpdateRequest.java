package by.mikola.order.dto.order;

import by.mikola.order.entity.enums.OrderStatus;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderUpdateRequest {
    @Size(max = 150)
    private String description;

    private OrderStatus status;
}