package af.demo.utils;

import af.demo.models.dto.ClientDTO;
import af.demo.models.entities.Client;

public class ClientMapper {
    public static ClientDTO toDTO(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(client.getId());
        clientDTO.setFullName(client.getFullName());
        if (client.getAccountings() != null)
            clientDTO.setAccountingList(client.getAccountings().stream().map(AccountingMapper::toDTO).toList());
        return clientDTO;
    }

}
