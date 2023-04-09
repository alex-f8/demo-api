package af.demo.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorBodyDTO {
    @JsonProperty("errorTitle")
    private String errorTitle;
    @JsonProperty("errorDescription")
    private Map<String, String> errorDescription;
}
