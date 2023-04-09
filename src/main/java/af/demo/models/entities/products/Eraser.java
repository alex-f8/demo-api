package af.demo.models.entities.products;

import af.demo.models.entities.Product;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@DiscriminatorValue(value = "ERASER")
public class Eraser extends Product {
    @Column(name = "param_1")
    private Boolean soft;
}
