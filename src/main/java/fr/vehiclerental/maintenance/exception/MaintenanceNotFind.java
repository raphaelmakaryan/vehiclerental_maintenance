package fr.vehiclerental.maintenance.exception;

public class MaintenanceNotFind extends RuntimeException {
    public MaintenanceNotFind() {
        super("Vehicle maintenance records were not found.");
    }
}
