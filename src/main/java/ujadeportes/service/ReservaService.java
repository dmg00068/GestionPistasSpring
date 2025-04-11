package ujadeportes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ujadeportes.model.Reserva;
import ujadeportes.repository.ReservaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    private ReservaRepository reservaRepository;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    /**
     * Metodo para obtener todas las reservas
     * @return Lista con todas las reservas
     */
    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }

    /**
     * Metodo para obtener una reserva dado su id
     * @param id Id de la reserva
     * @return Reserva con el id dado
     */
    public Optional<Reserva> findById(Long id) {
        return reservaRepository.findById(id);
    }

    /**
     * Metodo para guardar una reserva
     * @param reserva Reserva a guardar
     * @return Reserva guardada
     */
    public Reserva save(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    /**
     * Metodo para eliminar una reserva
     * @param reserva Reserva a eliminar
     */
    public void delete(Reserva reserva) {
        reservaRepository.delete(reserva);
    }
}