package af.demo.controllers;

import af.demo.models.dto.AmountDTO;
import af.demo.models.dto.ProductDTO;
import af.demo.models.entities.Product;
import af.demo.services.AccountingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static af.demo.utils.ProductMapper.toDTO;

@Slf4j
@RestController
@RequestMapping("/api/accounting")
public class AccountingController {
    private final AccountingService accountingService;

    public AccountingController(AccountingService accountingService) {
        this.accountingService = accountingService;
    }

    @PostMapping(value = "/add-amount/{productId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ProductDTO> addAmount(@PathVariable(value = "productId") Long productId, @RequestBody AmountDTO amountDTO) {
        Product product = accountingService.addAmount(productId, amountDTO);
        return new ResponseEntity<>(toDTO(product), HttpStatus.OK);
    }

    @PostMapping(value = "/get-product-to-client/{productId}/{clientId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ProductDTO> getProductForClient(@PathVariable(value = "productId") Long productId, @PathVariable(value = "clientId") Long clientId, @RequestBody AmountDTO amountDTO) {
        Product product = accountingService.getProductToClient(productId, clientId, amountDTO);
        return new ResponseEntity<>(toDTO(product), HttpStatus.OK);
    }

}
