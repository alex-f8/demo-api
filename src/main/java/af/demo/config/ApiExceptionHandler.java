package af.demo.config;

import af.demo.models.dto.ErrorBodyDTO;
import af.demo.exceptions.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler({ApiException.class})
    public ResponseEntity<ErrorBodyDTO> handleApiException(ApiException apiException) {
        apiException.printStackTrace();
        ErrorBodyDTO errorBodyDTO = new ErrorBodyDTO(apiException.getErrorTitle(), apiException.getErrorDescription());
        return new ResponseEntity<>(errorBodyDTO, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorBodyDTO> handleApiInnerExceptions(Exception exception) {
        // send exception message is not necessarily
        ErrorBodyDTO errorBodyDTO = new ErrorBodyDTO("Some exception on server side", Map.of("description", exception.getMessage()));
        return new ResponseEntity<>(errorBodyDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
