package by.mikola.order.dto.order;

import by.mikola.order.entity.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {
    private String id;
    private String description;
    private OrderStatus status;
    private String clientId;
}
