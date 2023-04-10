package af.demo.models.entities.products;

import af.demo.models.entities.Product;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue(value = "ERASER")
public class Eraser extends Product {
    @Column(name = "param_1")
    private String soft;
}
