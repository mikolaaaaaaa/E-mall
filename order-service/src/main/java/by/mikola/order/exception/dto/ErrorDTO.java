package by.mikola.order.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ErrorDTO {
    private Integer status;
    private String error;
    private String path;
    private String message;
    private Long timestamp;
}