package af.demo.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ClientDTO {
    private Long id;
    private String fullName;
    private List<AccountingDTO> accountingList;
}
