package co.ao.BotaoDePanico.Exception;

import co.ao.BotaoDePanico.error.OtherErrorDetails;
import co.ao.BotaoDePanico.error.ResourceNotFoundDetails;
import co.ao.BotaoDePanico.error.ResourceNotFoundException;
import co.ao.BotaoDePanico.error.ValidationErrorDetails;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author Airton Leal
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handlerResourceNotFoundException(ResourceNotFoundException rnfException) {
        ResourceNotFoundDetails rnfDetails = ResourceNotFoundDetails
                .builder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Resource Not Found")
                .detail(rnfException.getMessage())
                .developerMessage(rnfException.getClass().getName())
                .build();
        return new ResponseEntity<>(rnfDetails, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manvException, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FieldError> fieldError = manvException.getBindingResult().getFieldErrors();
        String fields = fieldError.stream().map(FieldError::getField).collect(Collectors
                .joining(","));

        String fieldMessage = fieldError.stream().map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));

        ValidationErrorDetails validationErrorDetails = ValidationErrorDetails
                .builder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Field validation Error")
                .detail("Verifica se estás a cumprir com as regras dos tamanhos e tipos de dados das variáveis")
                .developerMessage(manvException.getClass().getName())
                .field(fields)
                .fieldMessage(fieldMessage)
                .build();
        return new ResponseEntity<>(validationErrorDetails, HttpStatus.BAD_REQUEST);
    }
    
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception rnfException, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        OtherErrorDetails otherErrorDetails = OtherErrorDetails
                .builder()
                .timestamp(new Date().getTime())
                .status(status.value())
                .title("Internal Exception")
                .detail(rnfException.getMessage())
                .developerMessage(rnfException.getClass().getName())
                .build();
        return new ResponseEntity<>(otherErrorDetails, headers, HttpStatus.BAD_REQUEST);
    }

}
