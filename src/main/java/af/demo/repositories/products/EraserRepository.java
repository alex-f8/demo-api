package af.demo.repositories.products;

import af.demo.models.entities.products.Eraser;
import af.demo.models.entities.products.Pen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EraserRepository extends JpaRepository<Eraser, Long> {
}
