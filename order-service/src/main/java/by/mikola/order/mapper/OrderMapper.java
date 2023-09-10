package by.mikola.order.mapper;

import by.mikola.order.dto.order.OrderDTO;
import by.mikola.order.dto.order.OrderUpdateRequest;
import by.mikola.order.entity.order.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper
public interface OrderMapper {

    OrderDTO toDto(Order order);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "clientId", ignore = true)
    OrderDTO toDto(OrderUpdateRequest updateRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "clientId", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "description", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "status", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget Order order, OrderDTO orderDto);

}
