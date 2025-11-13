package fr.vehiclerental.maintenance.exception;

public class VehicleType extends RuntimeException {
    public VehicleType() {
        super("The type of vehicle was not found");
    }
}
