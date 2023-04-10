package af.demo.services;

import af.demo.exceptions.ApiException;
import af.demo.models.dto.AmountDTO;
import af.demo.models.entities.Accounting;
import af.demo.models.entities.Product;
import af.demo.repositories.AccountingRepository;
import af.demo.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private final ProductRepository productRepository;
    private final AccountingRepository accountingRepository;

    public TransactionService(ProductRepository productRepository, AccountingRepository accountingRepository) {
        this.productRepository = productRepository;
        this.accountingRepository = accountingRepository;
    }

    @Transactional
    public Product addAmount(Long productId, AmountDTO amountDTO) {
        Product product = productRepository.findByIdSafe(productId).orElseThrow(
                () -> new ApiException("product with id=%s dont found".formatted(productId))
        );
        product.setAmount(product.getAmount() + amountDTO.getAmount());
        return product;
    }

    @Transactional
    public void subtractAmount(Long productId, Long clientId, Integer amount) {
        Product product = productRepository.findByIdSafe(productId).orElseThrow(
                () -> new ApiException("product with id=%s dont found".formatted(productId))
        );
        product.setAmount(product.getAmount() - amount);
        if (product.getAmount() < 0)
            throw new ApiException("client cant take product, because peaces of amount is not enough");
        Accounting accounting = new Accounting();
        accounting.setProductId(productId);
        accounting.setClientId(clientId);
        accounting.setAmount(amount);
        accountingRepository.save(accounting);
    }
}
