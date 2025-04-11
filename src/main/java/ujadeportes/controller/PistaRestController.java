package ujadeportes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ujadeportes.model.Pista;
import ujadeportes.service.PistaService;

import java.util.List;
import java.util.Optional;

@RestController
public class PistaRestController {

    private final PistaService pistaService;
    @Autowired
    public PistaRestController(PistaService pistaService) {
        this.pistaService = pistaService;
    }

    @GetMapping("/api/pistas")
    public List<Pista> findAll() {return pistaService.findAll();}

    @GetMapping("/api/pistas/{id}")
    public ResponseEntity<Pista> findById(@PathVariable Long id) {
        Optional<Pista> pista = pistaService.findById(id);
        return pista.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/api/pistas")
    public Pista save(@RequestBody Pista pista) { return pistaService.save(pista);}

    @DeleteMapping("/api/pistas/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Pista> pista = pistaService.findById(id);
        pista.ifPresent(pistaService::delete);
    }

    @PostMapping("/api/pistas/inicializar")
    public void inicializarFranjasHorarias(@RequestBody Pista pista) { pistaService.inicializarFranjasHorarias(pista);}

    @PostMapping("/api/pistas/isDisponible")
    public boolean isDisponible(@RequestBody Pista pista, @RequestParam String inicio, @RequestParam String fin) {return pistaService.isDisponible(pista, inicio, fin);}

    @PostMapping("/api/pistas/reservarFranja")
    public boolean reservarFranja(@RequestBody Pista pista, @RequestParam String inicio, @RequestParam String fin) {return pistaService.reservarFranja(pista, inicio, fin);}

    @PostMapping("/api/pistas/liberarFranja")
    public boolean liberarFranja(@RequestBody Pista pista, @RequestParam String inicio, @RequestParam String fin) {return pistaService.liberarFranja(pista, inicio, fin);}
    }