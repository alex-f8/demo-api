package af.demo.services;

import af.demo.exceptions.ApiException;
import af.demo.models.entities.Client;
import af.demo.repositories.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Page<Client> getClients(int page, int size) {
        return clientRepository.findAll(PageRequest.of(page, size, Sort.Direction.ASC, "id"));
    }

    public Client getClientById(Long clientId) {
        return get(clientId);
    }

    Client get(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new ApiException("Client with id=%s not found".formatted(id)));
    }
}
