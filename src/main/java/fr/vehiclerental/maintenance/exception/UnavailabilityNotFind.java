package fr.vehiclerental.maintenance.exception;

public class UnavailabilityNotFind extends RuntimeException {
    public UnavailabilityNotFind() {
        super("The maintenance schedule defined for this vehicle was not found.");
    }
}
