package ujadeportes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ujadeportes.model.Pista;

public interface PistaRepository extends JpaRepository<Pista, Long> {
}