package ujadeportes.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Resenia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Long pistaId;  // Añadir una referencia a la pista

    @Min(value = 1, message = "La puntuacion debe ser mayor o igual a 1")
    @Max(value = 5, message = "Las puntuacion debe ser menor o igual a 5")
    private Float puntuacion;

    @Size(max = 50, message = "El comentario no debe tener más de {max} caracteres")
    private String comentario;

    public Resenia() {
    }

    public Resenia(Integer id, Long pistaId, Float puntuacion, String comentario) {
        this.id = id;
        this.pistaId = pistaId;  // Asociar la reseña a una pista
        this.puntuacion = puntuacion;
        this.comentario = comentario;
    }

    public Resenia(Resenia orig) {
        this.id = orig.id;
        this.pistaId = orig.pistaId;
        this.puntuacion = orig.puntuacion;
        this.comentario = orig.comentario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getPistaId() {
        return pistaId;
    }

    public void setPistaId(Long pistaId) {
        this.pistaId = pistaId;
    }

    public Float getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Float puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}