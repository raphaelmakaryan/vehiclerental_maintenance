package fr.vehiclerental.maintenance.exception;

public class VehicleAlreadyReserved extends RuntimeException {
    public VehicleAlreadyReserved() {
        super("Vehicle maintenance records were not found.");
    }
}
