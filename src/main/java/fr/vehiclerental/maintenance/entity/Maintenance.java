package fr.vehiclerental.maintenance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "Maintenance")
public class Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_vehicle")
    private int idVehicle;

    @Column(name = "id_unavailabilty")
    private int idUnavailability;

    public Maintenance() {
        super();
    }

    public int getIdUnavailability() {
        return idUnavailability;
    }

    public void setIdUnavailability(int idUnavailability) {
        this.idUnavailability = idUnavailability;
    }

    public int getidVehicle() {
        return idVehicle;
    }

    public void setidVehicle(int idVehicle) {
        this.idVehicle = idVehicle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Maintenance{" +
                "id=" + id +
                ", idVehicle=" + idVehicle +
                ", idUnavailability=" + idUnavailability +
                '}';
    }
}
