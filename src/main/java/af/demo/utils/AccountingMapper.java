package af.demo.utils;

import af.demo.models.dto.AccountingDTO;
import af.demo.models.entities.Accounting;

public class AccountingMapper {
    public static AccountingDTO toDTO(Accounting accounting) {
        AccountingDTO accountingDTO = new AccountingDTO();
        accountingDTO.setId(accounting.getId());
        accountingDTO.setClientId(accounting.getClientId());
        accountingDTO.setProductId(accounting.getProductId());
        accountingDTO.setAmount(accounting.getAmount());
        accountingDTO.setCreatedAt(accounting.getCreatedAt().toString());
        return accountingDTO;
    }

}
