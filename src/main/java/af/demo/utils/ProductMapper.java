package af.demo.utils;

import af.demo.exceptions.ApiException;
import af.demo.models.dto.ProductDTO;
import af.demo.models.entities.Product;
import af.demo.models.entities.products.Eraser;
import af.demo.models.entities.products.Note;
import af.demo.models.entities.products.Pen;
import af.demo.models.enums.ProductEnum;

public class ProductMapper {

    public static Product fromDTO(ProductDTO productDTO) {
        String type;
        try {
            type = ProductEnum.valueOf(productDTO.getType().trim()).toString();
        } catch (IllegalArgumentException e) {
            throw new ApiException("type of product is incorrect");
        }
        Product product = productCast(type, productDTO.getParam1());
        product.setTitle(productDTO.getTitle());
        product.setDescription(productDTO.getDescription());
        product.setType(type);
        product.setAmount(productDTO.getAmount());
        return product;
    }

    private static Product productCast(String type, String param1) {
        if (type.equals("pen")) {
            Pen pen = new Pen();
            pen.setColor(param1);
            return pen;
        }
        if (type.equals("note")) {
            Note note = new Note();
            note.setPages(Integer.valueOf(param1));
            return note;
        }
        if (type.equals("eraser")) {
            Eraser eraser = new Eraser();
            eraser.setSoft(param1);
            return eraser;
        }
        return new Product();
    }

    public static ProductDTO toDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setTitle(product.getTitle());
        productDTO.setDescription(product.getDescription());
        productDTO.setAmount(product.getAmount());
        productDTO.setType(product.getClass().getSimpleName().toLowerCase());
        return productDTO;
    }


}
