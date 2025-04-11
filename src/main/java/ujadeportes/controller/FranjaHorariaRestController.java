package ujadeportes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ujadeportes.model.FranjaHoraria;
import ujadeportes.service.FranjaHorariaService;

import java.util.List;
import java.util.Optional;

@RestController
public class FranjaHorariaRestController {

    private final FranjaHorariaService franjaHorariaService;


    @Autowired
    public FranjaHorariaRestController(FranjaHorariaService franjaHorariaService) {
        this.franjaHorariaService = franjaHorariaService;
    }

    /**
     * Método para obtener todas las franjas horarias
     * @return Lista con todas las franjas
     */
    @GetMapping("/api/franjas")
    public List<FranjaHoraria> findAll() {
        return franjaHorariaService.findAll();
    }

    /**
     * Método para obtener una franja horaria dado su id
     * @param id Id de la franja
     * @return Franja horaria con el id dado
     */
    @GetMapping("/api/franjas/{id}")
    public ResponseEntity<FranjaHoraria> findById(@PathVariable Long id) {
        Optional<FranjaHoraria> franja = franjaHorariaService.findById(id);
        return franja.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Método para crear una franja horaria
     * @param franja Franja horaria a crear
     * @return Franja horaria creada
     */
    @PostMapping("/api/franjas")
    public FranjaHoraria save(@RequestBody FranjaHoraria franja) {
        return franjaHorariaService.save(franja);
    }

    @DeleteMapping("/api/franjas")
    public void delete(@RequestBody FranjaHoraria franja) { franjaHorariaService.delete(franja);}
}