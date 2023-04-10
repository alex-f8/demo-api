package af.demo.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(schema = "products", name = "accounting")
public class Accounting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "client_id")
    private Long clientId;
    @Column(name = "amount")
    private Integer amount;
    @ManyToOne
    @JoinColumn(name = "client_id", insertable = false, updatable = false)
    private Client client;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    {
        this.createdAt = LocalDateTime.now();
    }
}
