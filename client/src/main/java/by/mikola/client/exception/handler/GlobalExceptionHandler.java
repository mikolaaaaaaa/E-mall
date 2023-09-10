package by.mikola.client.exception.handler;

import by.mikola.client.exception.NotFoundException;
import by.mikola.client.exception.dto.ErrorDTO;
import by.mikola.client.exception.dto.ValidationErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDTO> handleNotFound(NotFoundException ex, HttpServletRequest req) {
        log.error("Error handled", ex);
        ErrorDTO errorDto = buildErrorDto(ex, req, HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDTO> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest req) {
        log.error("Error handled", ex);
        List<ValidationErrorDTO.FieldError> fieldErrors = ex.getFieldErrors().stream()
                .map(e -> ValidationErrorDTO.FieldError.builder()
                        .field(e.getField())
                        .error(e.getDefaultMessage())
                        .build())
                .toList();
        ErrorDTO errorDto = buildErrorDto(ex, req, HttpStatus.BAD_REQUEST);

        ValidationErrorDTO validationErrorDto = new ValidationErrorDTO(errorDto, "Validation Error", fieldErrors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrorDto);
    }

    private ErrorDTO buildErrorDto(Exception ex, HttpServletRequest req, HttpStatus httpStatus) {
        return ErrorDTO.builder()
                .timestamp(Instant.now().getEpochSecond())
                .error(httpStatus.getReasonPhrase())
                .status(httpStatus.value())
                .path(req.getServletPath())
                .message(ex.getMessage())
                .build();
    }
}