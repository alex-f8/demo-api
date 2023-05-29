package af.demo.services;

import af.demo.exceptions.ApiException;
import af.demo.models.dto.AmountDTO;
import af.demo.models.entities.Client;
import af.demo.models.entities.Product;
import af.demo.repositories.ClientRepository;
import af.demo.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.OptimisticLockingFailureException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AccountingServiceTest {
    private static final Long PRODUCT_ID = 1L;
    private static final Long CLIENT_ID = 1L;
    ProductRepository productRepository = mock(ProductRepository.class);
    ClientRepository clientRepository = mock(ClientRepository.class);
    TransactionService transactionService = mock(TransactionService.class);
    AccountingService accountingService = new AccountingService(productRepository, clientRepository, transactionService);
    AmountDTO amountDTO1 = mock(AmountDTO.class);
    Product product = mock(Product.class);
    Client client = mock(Client.class);


    @BeforeEach
    void setUp() {
    }

    @Test
    void addAmount() {
        when(transactionService.addAmount(PRODUCT_ID, amountDTO1)).thenReturn(product);
        var resp = accountingService.addAmount(PRODUCT_ID, amountDTO1);

        assertEquals(product, resp);
    }

    @Test
    void getProductToClient() {
        when(clientRepository.findById(-1L)).thenReturn(Optional.of(client));
        when(productRepository.findById(PRODUCT_ID)).thenReturn(Optional.of(product));
        var resp = accountingService.getProductToClient(PRODUCT_ID, -CLIENT_ID, amountDTO1);

        assertEquals(product, resp);
    }

    @Test
    void getProductToClient_whenClientRepositoryThrowsException() {
        when(clientRepository.findById(-1L)).thenThrow(ApiException.class);
        when(productRepository.findById(PRODUCT_ID)).thenReturn(Optional.of(product));

        assertThrows(ApiException.class, () -> {
            accountingService.getProductToClient(PRODUCT_ID, CLIENT_ID, amountDTO1);
        });
    }

    @Test
    void getProductToClient_whenTransactionRepositoryThrowsException() {
        when(clientRepository.findById(-1L)).thenReturn(Optional.of(client));

//        when((transactionService).me)
//                .thenThrow(ApiException.class)
//                .thenThrow(IllegalArgumentException.class)
//                .thenThrow(OptimisticLockingFailureException.class);
//        when(transactionService.subtractAmount()).thenThrow(IllegalArgumentException.class);
//        when(transactionService.subtractAmount()).thenThrow(OptimisticLockingFailureException.class);

        when(productRepository.findById(PRODUCT_ID)).thenReturn(Optional.of(product));

        assertThrows(Exception.class, () -> {
            accountingService.getProductToClient(PRODUCT_ID, CLIENT_ID, amountDTO1);
        });;
    }
}