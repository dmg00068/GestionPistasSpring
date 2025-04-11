package ujadeportes.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Entidad que representa una pista deportiva.
 */
@Entity
public class Pista implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String deporteAsignado;
    private String horario;
    private String campus;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<FranjaHoraria> franjasHorarias;

    /**
     * Constructor por defecto.
     */
    public Pista() {
        this.franjasHorarias = new ArrayList<>();
    }

    /**
     * Constructor con parámetros.
     *
     * @param id               Identificador único de la pista
     * @param nombre           Nombre de la pista
     * @param deporteAsignado  Deporte que se practica en la pista
     * @param horario          Horario general de apertura de la pista
     * @param campus           Campus donde se ubica la pista
     */
    public Pista(Long id, String nombre, String deporteAsignado, String horario, String campus) {
        this.id = id;
        this.nombre = nombre;
        this.deporteAsignado = deporteAsignado;
        this.horario = horario;
        this.campus = campus;
        this.franjasHorarias = new ArrayList<>();

        // Inicializar franjas horarias basadas en el horario general
        // Por ejemplo, si el horario es 09:00-22:00, crear franjas de 1 hora
        inicializarFranjasHorarias();
    }

    /**
     * Método para inicializar las franjas horarias disponibles para la pista
     * basándonos en el horario general de apertura.
     */
    private void inicializarFranjasHorarias() {
        // Parseamos el horario (formato: HH:MM-HH:MM)
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
                franjasHorarias.add(new FranjaHoraria(inicio, fin, false));
            }
        }
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDeporteAsignado() {
        return deporteAsignado;
    }

    public void setDeporteAsignado(String deporteAsignado) {
        this.deporteAsignado = deporteAsignado;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
        // Al cambiar el horario, actualizamos las franjas horarias
        franjasHorarias.clear();
        inicializarFranjasHorarias();
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public List<FranjaHoraria> getFranjasHorarias() {
        return franjasHorarias;
    }

    public void setFranjasHorarias(List<FranjaHoraria> franjasHorarias) {
        this.franjasHorarias = franjasHorarias;
    }

    /**
     * Método para verificar si la pista está disponible en una franja horaria específica.
     *
     * @param inicio Hora de inicio de la franja
     * @param fin    Hora de fin de la franja
     * @return true si está disponible, false si está reservada
     */
    public boolean isDisponible(String inicio, String fin) {
        for (FranjaHoraria franja : franjasHorarias) {
            if (franja.getHoraInicio().equals(inicio) && franja.getHoraFin().equals(fin)) {
                return !franja.isReservada();
            }
        }
        return false; // Si no existe la franja, consideramos que no está disponible
    }

    /**
     * Método para reservar una franja horaria específica.
     *
     * @param inicio Hora de inicio de la franja
     * @param fin    Hora de fin de la franja
     * @return true si se pudo reservar, false si ya estaba reservada
     */
    public boolean reservarFranja(String inicio, String fin) {
        for (FranjaHoraria franja : franjasHorarias) {
            if (franja.getHoraInicio().equals(inicio) && franja.getHoraFin().equals(fin)) {
                if (!franja.isReservada()) {
                    franja.setReservada(true);
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    /**
     * Método para cancelar la reserva de una franja horaria reservada.
     *
     * @param inicio Hora de inicio de la franja
     * @param fin    Hora de fin de la franja
     * @return true si se pudo liberar, false si no estaba reservada
     */
    public boolean liberarFranja(String inicio, String fin) {
        for (FranjaHoraria franja : franjasHorarias) {
            if (franja.getHoraInicio().equals(inicio) && franja.getHoraFin().equals(fin)) {
                if (franja.isReservada()) {
                    franja.setReservada(false);
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pista pista = (Pista) o;
        return Objects.equals(id, pista.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Pista{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", deporteAsignado='" + deporteAsignado + '\'' +
                ", horario='" + horario + '\'' +
                ", campus='" + campus + '\'' +
                '}';
    }
}