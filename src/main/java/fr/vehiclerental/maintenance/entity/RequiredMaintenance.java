package fr.vehiclerental.maintenance.entity;

public class RequiredMaintenance {
    private int id_vehicle;
    private int id_unavailability;

    public RequiredMaintenance(int id_vehicle, int id_unavailability) {
        this.id_vehicle = id_vehicle;
        this.id_unavailability = id_unavailability;
    }

    public int getId_vehicle() {
        return id_vehicle;
    }

    public void setId_vehicle(int id_vehicle) {
        this.id_vehicle = id_vehicle;
    }

    public int getId_unavailability() {
        return id_unavailability;
    }

    public void setId_unavailability(int id_unavailability) {
        this.id_unavailability = id_unavailability;
    }

}
