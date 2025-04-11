package ujadeportes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ujadeportes.model.Reserva;
import ujadeportes.service.ReservaService;

import java.util.List;
import java.util.Optional;

@RestController
public class ReservaRestController {

    private final ReservaService reservaService;

    @Autowired
    public ReservaRestController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    /**
     * Metodo para obtener todas las reservas
     * @return Lista con todas las reservas
     */
    @GetMapping("/api/reservas")
    public List<Reserva> findAll() {
        return reservaService.findAll();
    }

    /**
     * Metodo para obtener una reserva dado su id
     * @param id Id de la reserva
     * @return Reserva con el id dado
     */
    @GetMapping("/api/reservas/{id}")
    public ResponseEntity<Reserva> findById(@PathVariable Long id) {
        Optional<Reserva> reserva = reservaService.findById(id);
        return reserva.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Metodo para guardar una reserva
     * @param reserva Reserva a guardar
     * @return Reserva guardada
     */
    @PostMapping("/api/reservas")
    public Reserva save(@RequestBody Reserva reserva) { return reservaService.save(reserva); }

    @DeleteMapping("/api/reservas")
    public void delete(@RequestBody Reserva reserva) { reservaService.delete(reserva); }
}
