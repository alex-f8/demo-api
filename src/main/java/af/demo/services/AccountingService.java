package af.demo.services;

import af.demo.exceptions.ApiException;
import af.demo.models.dto.AmountDTO;
import af.demo.models.entities.Product;
import af.demo.repositories.ClientRepository;
import af.demo.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AccountingService {

    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;
    private final TransactionService transactionService;

    public AccountingService(ProductRepository productRepository, ClientRepository clientRepository, TransactionService transactionService) {
        this.productRepository = productRepository;
        this.clientRepository = clientRepository;
        this.transactionService = transactionService;
    }


    @Transactional
    public Product addAmount(Long productId, AmountDTO amountDTO) {
        return transactionService.addAmount(productId, amountDTO);
    }

    public Product getProductToClient(Long productId, Long clientId, AmountDTO amountDTO) {
        clientRepository.findById(clientId).orElseThrow(
                () -> new ApiException("wrong client id=%s".formatted(clientId))
        );
        transactionService.subtractAmount(productId, clientId, amountDTO.getAmount());
        return productRepository.findById(productId).get();
    }

}
