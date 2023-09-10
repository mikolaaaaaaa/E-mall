package by.mikola.order.exception.handler;

import by.mikola.order.exception.ConflictException;
import by.mikola.order.exception.NotFoundException;
import by.mikola.order.exception.dto.ErrorDTO;
import by.mikola.order.exception.dto.ValidationErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static final String ERROR_HANDLED_MESSAGE = "Error handled";

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDTO> handleNotFound(NotFoundException ex, HttpServletRequest req) {
        log.error(ERROR_HANDLED_MESSAGE, ex);
        ErrorDTO errorDto = buildErrorDto(ex, req, HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorDTO> handleConflict(ConflictException ex, HttpServletRequest req) {
        log.error(ERROR_HANDLED_MESSAGE, ex);
        ErrorDTO errorDto = buildErrorDto(ex, req, HttpStatus.CONFLICT);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDTO> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest req) {
        log.error(ERROR_HANDLED_MESSAGE, ex);

        List<ValidationErrorDTO.FieldError> fieldErrors = ex.getFieldErrors().stream()
                .map(e -> ValidationErrorDTO.FieldError.builder()
                        .field(e.getField())
                        .error(e.getDefaultMessage())
                        .build())
                .toList();
        String message = ex.getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .findFirst()
                .orElse("Validation Error");
        ErrorDTO errorDto = buildErrorDto(ex, req, HttpStatus.BAD_REQUEST);

        ValidationErrorDTO validationErrorDto = new ValidationErrorDTO(errorDto, message, fieldErrors);
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