package af.demo.services;

import af.demo.exceptions.ApiException;
import af.demo.models.entities.Product;
import af.demo.models.entities.products.Eraser;
import af.demo.models.entities.products.Note;
import af.demo.models.entities.products.Pen;
import af.demo.repositories.ProductRepository;
import af.demo.repositories.products.EraserRepository;
import af.demo.repositories.products.NoteRepository;
import af.demo.repositories.products.PenRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final PenRepository penRepository;
    private final NoteRepository noteRepository;
    private final EraserRepository eraserRepository;


    public ProductService(ProductRepository productRepository, PenRepository penRepository, NoteRepository noteRepository, EraserRepository eraserRepository) {
        this.productRepository = productRepository;
        this.penRepository = penRepository;
        this.noteRepository = noteRepository;
        this.eraserRepository = eraserRepository;
    }

    public Page<Product> getProducts(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size, Sort.Direction.ASC, "id"));
    }

    /**
     * we can create product only once
     * then we can only update amount of products
     */
    @Transactional
    public Product createProduct(Product product) {
        Optional<Product> optProd = productRepository.findByType(product.getType().toUpperCase());
        if (optProd.isPresent()) return optProd.get();
        Product resp;
        if (product instanceof Pen) resp = penRepository.save((Pen) product);
        else if (product instanceof Note) resp = noteRepository.save((Note) product);
        else if (product instanceof Eraser) resp = eraserRepository.save((Eraser) product);
        else throw new ApiException("unknown type to save");
        return resp;
    }

    public Product getProductById(Long productId) {
        return get(productId);
    }

    @Transactional
    public Product updateProduct(Long productId, Product product) {
        Product existingProduct = get(productId);
        existingProduct.setTitle(product.getTitle());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setAmount(product.getAmount());
        return existingProduct;
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    Product get(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ApiException("Product with id=%s not found".formatted(id)));
    }
}
