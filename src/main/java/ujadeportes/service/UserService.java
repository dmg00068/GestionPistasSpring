package ujadeportes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ujadeportes.model.User;
import ujadeportes.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Metodo para obtener todos los usuarios
     * @return Lista con todos los usuarios
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Metodo para obtener un usuario dado su id
     * @param id Id del usuario
     * @return Usuario con el id dado
     */
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Metodo para guardar un usuario
     * @param user Usuario a guardar
     * @return Usuario guardado
     */
    public User save(User user) {
        return userRepository.save(user);
    }

    /**
     * Metodo para eliminar un usuario
     * @param user Usuario a eliminar
     */
    public void delete(User user) {
        userRepository.delete(user);
    }
}
