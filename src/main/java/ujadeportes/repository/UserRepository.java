package ujadeportes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ujadeportes.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}