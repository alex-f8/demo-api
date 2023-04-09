package af.demo.controllers;

import af.demo.models.dto.AmountDTO;
import af.demo.models.dto.ProductDTO;
import af.demo.models.entities.Product;
import af.demo.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static af.demo.utils.ProductMapper.fromDTO;
import static af.demo.utils.ProductMapper.toDTO;

@Slf4j
@RestController
@RequestMapping("/api/products")
public class ProductController {
    public final ProductService productService;
    public final ObjectMapper objectMapper;

    public ProductController(ProductService productService, ObjectMapper objectMapper) {
        this.productService = productService;
        this.objectMapper = objectMapper;
    }

    @RequestMapping(method = RequestMethod.GET, params = {"page", "size"}, produces = "application/json")
    public ResponseEntity<Page<ProductDTO>> getProducts(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page, @RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
        var resp = productService.getProducts(page, size).map(n -> objectMapper.convertValue(n, ProductDTO.class));
        log.info("> found {} elements ", resp.getSize());
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        var resp = productService.createProduct(fromDTO(productDTO));
        log.info("> product added : {}", resp);
        return new ResponseEntity<>(toDTO(resp), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{productId}", produces = "application/json")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable(value = "productId") Long productId) {
        return new ResponseEntity<>(objectMapper.convertValue(productService.getProductById(productId), ProductDTO.class), HttpStatus.OK);
    }

    @PutMapping(value = "/{productId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable(value = "productId") Long productId, @RequestBody ProductDTO productDTO) {
        var resp = productService.updateProduct(productId, objectMapper.convertValue(productDTO, Product.class));
        return new ResponseEntity<>(objectMapper.convertValue(resp, ProductDTO.class), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(value = "productId") Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }

}
