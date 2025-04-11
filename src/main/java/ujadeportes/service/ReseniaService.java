package ujadeportes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ujadeportes.model.Resenia;
import ujadeportes.repository.ReseniaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReseniaService {

    private ReseniaRepository reseniaRepository;

    @Autowired
    public ReseniaService(ReseniaRepository reseniaRepository) {
        this.reseniaRepository = reseniaRepository;
    }

    /**
     * Metodo para obtener todas las reseñas
     * @return Lista con todas las reseñas
     */
    public List<Resenia> findAll() {
        return reseniaRepository.findAll();
    }

    /**
     * Metodo para obtener una reseña dado su id
     * @param id Id de la reseña
     * @return Reseña con el id dado
     */
    public Optional<Resenia> findById(Integer id) {
        return reseniaRepository.findById(id);
    }

    /**
     * Metodo para guardar una reseña
     * @param resenia Reseña a guardar
     * @return Reseña guardada
     */
    public Resenia save(Resenia resenia) {
        return reseniaRepository.save(resenia);
    }

    /**
     * Metodo para eliminar una reseña
     * @param resenia Reseña a eliminar
     */
    public void delete(Resenia resenia) {
        reseniaRepository.delete(resenia);
    }



}