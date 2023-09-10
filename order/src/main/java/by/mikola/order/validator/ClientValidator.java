package by.mikola.order.validator;

import by.mikola.order.dto.client.ClientDTO;
import by.mikola.order.dto.order.OrderCreateRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class ClientValidator implements ConstraintValidator<ValidClient, OrderCreateRequest> {

    @Override
    public boolean isValid(OrderCreateRequest createRequest, ConstraintValidatorContext constraintValidatorContext) {
        ClientDTO client = createRequest.getClient();
        return client != null &&
                (client.getId() != null || client.getEmail() != null);
    }
}
