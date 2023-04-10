package af.demo.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AccountingDTO {
    private Long id;
    private Long productId;
    private Long clientId;
    private Integer amount;
    private String createdAt;
}
