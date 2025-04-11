package ujadeportes.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Clase que representa una franja horaria para reservas de pistas.
 */
@Entity
//@Table(name = "franjas_horarias")
public class FranjaHoraria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String horaInicio;
    private String horaFin;
    private boolean reservada;
    private Long reservaId; // ID de la reserva asociada, si existe

    /**
     * Constructor por defecto.
     */
    public FranjaHoraria() {
    }

    /**
     * Constructor con parámetros.
     *
     * @param horaInicio Hora de inicio de la franja (formato HH:MM)
     * @param horaFin Hora de fin de la franja (formato HH:MM)
     * @param reservada Indica si la franja está reservada
     */
    public FranjaHoraria(String horaInicio, String horaFin, boolean reservada) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.reservada = reservada;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public boolean isReservada() {
        return reservada;
    }

    public void setReservada(boolean reservada) {
        this.reservada = reservada;
    }

    public Long getReservaId() {
        return reservaId;
    }

    public void setReservaId(Long reservaId) {
        this.reservaId = reservaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FranjaHoraria that = (FranjaHoraria) o;
        return Objects.equals(horaInicio, that.horaInicio) &&
                Objects.equals(horaFin, that.horaFin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(horaInicio, horaFin);
    }

    @Override
    public String toString() {
        return horaInicio + " - " + horaFin;
    }
}