package af.demo.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class ApiException extends RuntimeException {
    private String errorTitle;
    private Map<String, String> errorDescription;

    public ApiException(String message) {
        super(message);
        this.errorTitle = message;
    }

    public ApiException(String errorTitle, Map<String, String> errorDescription) {
        this.errorTitle = errorTitle;
        this.errorDescription = errorDescription;
    }
}
