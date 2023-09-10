package by.mikola.client.exception.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ValidationErrorDTO extends ErrorDTO {
    private List<FieldError> fieldErrors;

    public ValidationErrorDTO(ErrorDTO errorDto, String message, List<FieldError> fieldErrors) {
        super(errorDto.getStatus(), errorDto.getError(), errorDto.getPath(), message, errorDto.getTimestamp());
        this.fieldErrors = fieldErrors;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class FieldError {
        private String field;
        private String error;
    }
}