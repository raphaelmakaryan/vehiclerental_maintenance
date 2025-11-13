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
public class  Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_vehicule")
    private int idVehicule;

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

    public int getIdVehicule() {
        return idVehicule;
    }

    public void setIdVehicule(int idVehicule) {
        this.idVehicule = idVehicule;
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
                ", idVehicule=" + idVehicule +
                ", idUnavailability=" + idUnavailability +
                '}';
    }
}
