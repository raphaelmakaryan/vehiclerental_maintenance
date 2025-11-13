package fr.vehiclerental.maintenance.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Repository;

@Repository
public class UnavailabilityDTO {
    private Integer id;
    private String typeVehicle;
    private String description;
    private int time;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeVehicle() {
        return typeVehicle;
    }

    public void setTypeVehicle(String typeVehicle) {
        this.typeVehicle = typeVehicle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Unavailability{" +
                "id=" + id +
                ", typeVehicle='" + typeVehicle + '\'' +
                ", description='" + description + '\'' +
                ", time=" + time +
                '}';
    }
}