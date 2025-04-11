package ujadeportes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ujadeportes.model.FranjaHoraria;
import ujadeportes.model.Pista;
import ujadeportes.repository.FranjaHorariaRepository;
import ujadeportes.repository.PistaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PistaService {

    private final PistaRepository pistaRepository;
    private final FranjaHorariaRepository franjaHorariaRepository;

    @Autowired
    public PistaService(PistaRepository pistaRepository, FranjaHorariaRepository franjaHorariaRepository) {
        this.pistaRepository = pistaRepository;
        this.franjaHorariaRepository = franjaHorariaRepository;
    }

    /**
     * Método para obtener todas las pistas
     *
     * @return Lista con todas las pistas
     */
    public List<Pista> findAll() {
        return pistaRepository.findAll();
    }

    /**
     * Método para obtener una pista dado su id
     *
     * @param id Id de la pista
     * @return Pista con el id dado
     */
    public Optional<Pista> findById(Long id) {
        return pistaRepository.findById(id);
    }

    public Pista save(Pista pista) {
        return pistaRepository.save(pista);
    }

    public void delete(Pista pista) {
        pistaRepository.delete(pista);
    }

    /**
     * Método para inicializar las franjas horarias disponibles para la pista
     * basándonos en el horario general de apertura.
     */
    public void inicializarFranjasHorarias(Pista pista) {
        // Parseamos el horario (formato: HH:MM-HH:MM)
        String horario = pista.getHorario();
        if (horario != null && horario.contains("-")) {
            String[] horas = horario.split("-");
            String horaInicio = horas[0].trim();
            String horaFin = horas[1].trim();

            // Convertimos a valores numéricos (solo para las horas)
            int horaInicioNum = Integer.parseInt(horaInicio.split(":")[0]);
            int horaFinNum = Integer.parseInt(horaFin.split(":")[0]);

            // Crear franjas horarias de 1 hora
            for (int hora = horaInicioNum; hora < horaFinNum; hora++) {
                String inicio = String.format("%02d:00", hora);
                String fin = String.format("%02d:00", hora + 1);
                FranjaHoraria franjaHoraria = new FranjaHoraria(inicio, fin, false);
                franjaHorariaRepository.save(franjaHoraria);
                pista.getFranjasHorarias().add(franjaHoraria);
            }
        }
    }

    /**
     * Método para verificar si la pista está disponible en una franja horaria específica.
     *
     * @param pista  Pista donde buscar
     * @param inicio Hora de inicio de la franja
     * @param fin    Hora de fin de la franja
     * @return True si está disponible, False si está reservada
     */
    public boolean isDisponible(Pista pista, String inicio, String fin) {
        return pista.isDisponible(inicio, fin);
    }

    /**
     * Método para reservar una franja horaria específica.
     *
     * @param pista  Pista donde reservar
     * @param inicio Hora de inicio de la franja
     * @param fin    Hora de fin de la franja
     * @return True si se pudo reservar, False si ya estaba reservada
     */
    public boolean reservarFranja(Pista pista, String inicio, String fin) {
        return pista.reservarFranja(inicio, fin);
    }

    public boolean liberarFranja(Pista pista, String inicio, String fin) {
        return pista.liberarFranja(inicio, fin);
    }
}