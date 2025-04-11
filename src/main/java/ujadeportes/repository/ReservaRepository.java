package ujadeportes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ujadeportes.model.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}