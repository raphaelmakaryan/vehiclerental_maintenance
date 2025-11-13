package fr.vehiclerental.maintenance.entity;

public class VehicleDTO {
    private int id;
    private String type;
    private String registration;


    public VehicleDTO(int id, String type, String registration) {
        this.id = id;
        this.type = type;
        this.registration = registration;

    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "VehicleDTO{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", registration='" + registration + '\'' +
                '}';
    }
}

