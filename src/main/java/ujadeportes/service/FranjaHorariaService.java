package ujadeportes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ujadeportes.model.FranjaHoraria;
import ujadeportes.repository.FranjaHorariaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FranjaHorariaService {

    private FranjaHorariaRepository franjaHorariaRepository;

    @Autowired
    public FranjaHorariaService(FranjaHorariaRepository franjaHorariaRepository) {
        this.franjaHorariaRepository = franjaHorariaRepository;
    }

    /**
     * Método para obtener todas las franjas horarias
     * @return Lista con todas las franjas
     */
    public List<FranjaHoraria> findAll() {
        return franjaHorariaRepository.findAll();
    }

    /**
     * Método para obtener una franja horaria dado su id
     * @param id Id de la franja
     * @return Franja horaria con el id dado
     */
    public Optional<FranjaHoraria> findById(Long id) {
        return franjaHorariaRepository.findById(id);
    }

    public FranjaHoraria save(FranjaHoraria franjaHoraria) {
        return franjaHorariaRepository.save(franjaHoraria);
    }

    public void delete(FranjaHoraria franjaHoraria) {
        franjaHorariaRepository.delete(franjaHoraria);
    }
}