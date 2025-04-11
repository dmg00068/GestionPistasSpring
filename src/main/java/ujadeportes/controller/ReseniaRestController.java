package ujadeportes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ujadeportes.model.Resenia;
import ujadeportes.service.ReseniaService;

import java.util.List;
import java.util.Optional;

@RestController
public class ReseniaRestController {

    private final ReseniaService reseniaService;


    @Autowired
    public ReseniaRestController(ReseniaService reseniaService) {
        this.reseniaService = reseniaService;
    }

    /**
     * Metodo para obtener todas las reseñas
     * @return Lista con todas las reseñas
     */
    @GetMapping("/api/resenias")
    public List<Resenia> findAll() {
        return reseniaService.findAll();
    }

    /**
     * Metodo para obtener una reseña dado su id
     * @param id Id de la reseña
     * @return Reseña con el id dado
     */
    @GetMapping("/api/resenias/{id}")
    public ResponseEntity<Resenia> findById(@PathVariable Integer id) {
        Optional<Resenia> resenia = reseniaService.findById(id);
        return resenia.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Metodo para guardar una reseña
     * @param resenia Reseña a guardar
     * @return Reseña guardada
     */
    @PostMapping("/api/resenias")
    public Resenia save(@RequestBody Resenia resenia) {
        return reseniaService.save(resenia);
    }

    /**
     * Metodo para eliminar una reseña
     * @param resenia Reseña a eliminar
     */
    @DeleteMapping("/api/resenias")
    public void delete(@RequestBody Resenia resenia) {
        reseniaService.delete(resenia);
    }
}