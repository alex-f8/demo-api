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

    public AccountingService(ProductRepository productRepository, ClientRepository clientRepository) {
        this.productRepository = productRepository;
        this.clientRepository = clientRepository;
    }

    @Transactional
    public Product addAmount(Long productId, AmountDTO amountDTO) {
        Product product = productRepository.findByIdSecured(productId).orElseThrow(
                () -> new ApiException("product with id=%s dont found".formatted(productId))
        );
        product.setAmount(product.getAmount() + amountDTO.getAmount());
        return product;
    }

    public Product getProductToClient(Long productId, Long clientId, AmountDTO amountDTO) {
        clientRepository.findById(clientId).orElseThrow(
                () -> new ApiException("wrong client id=%s".formatted(clientId))
        );
        subtractAmount(productId, amountDTO.getAmount());
        return productRepository.findById(productId).get();
    }

    @Transactional
    public void subtractAmount(Long productId, Integer amount) {
        Product product = productRepository.findByIdSecured(productId).orElseThrow(
                () -> new ApiException("product with id=%s dont found".formatted(productId))
        );
        product.setAmount(product.getAmount() - amount);
        if (product.getAmount() < 0)
            throw new ApiException("client cant take product, because peaces of amount is not enough");
    }
}
