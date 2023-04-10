package af.demo.controllers;

import af.demo.models.dto.ClientDTO;
import af.demo.services.ClientService;
import af.demo.utils.ClientMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static af.demo.utils.ClientMapper.toDTO;

@Slf4j
@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(params = {"page", "size"}, produces = "application/json")
    public ResponseEntity<Page<ClientDTO>> getClients(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page, @RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
        var resp = clientService.getClients(page, size);
        log.info("> found {} elements ", resp.getSize());
        return new ResponseEntity<>(resp.map(ClientMapper::toDTO), HttpStatus.OK);
    }

    @GetMapping(value = "/{productId}", produces = "application/json")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable(value = "productId") Long productId) {
        return new ResponseEntity<>(toDTO(clientService.getClientById(productId)), HttpStatus.OK);
    }
}
