package ujadeportes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ujadeportes.model.User;
import ujadeportes.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
public class UserRestController {

    private final UserService userService;


    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Metodo para obtener todos los usuarios
     * @return Lista con todos los usuarios
     */
    @GetMapping("/api/users")
    public List<User> findAll() {
        return userService.findAll();
    }

    /**
     * Metodo para obtener un usuario dado su id
     * @param id Id del usuario
     * @return Usuario con el id dado
     */
    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Metodo para guardar un usuario
     * @param user Usuario a guardar
     * @return Usuario guardado
     */
    @PostMapping("/api/users")
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    /**
     * Metodo para eliminar un usuario
     * @param id Id del usuario a eliminar
     */
    @DeleteMapping("/api/users/{id}")
    public void delete(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        user.ifPresent(userService::delete);
    }
}