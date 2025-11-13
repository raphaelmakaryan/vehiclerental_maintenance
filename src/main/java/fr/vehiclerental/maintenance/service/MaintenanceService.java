package fr.vehiclerental.maintenance.service;

import fr.vehiclerental.maintenance.entity.Maintenance;
import fr.vehiclerental.maintenance.entity.ReservationDTO;
import fr.vehiclerental.maintenance.entity.UnavailabilityDTO;
import fr.vehiclerental.maintenance.exception.MaintenanceNotFind;
import fr.vehiclerental.maintenance.exception.UnavailabilityNotFind;
import fr.vehiclerental.maintenance.exception.VehicleNotFind;
import lombok.extern.slf4j.Slf4j;
import fr.vehiclerental.maintenance.entity.VehicleDTO;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

@Slf4j
@Service

public class MaintenanceService {

    /**
     * Methode pour appeller l'api Vehicle
     *
     * @param idVehicle Id du vehicule demandé
     * @return Retourne la liste de vehicule
     */
    public List<VehicleDTO> requestVehicle(int idVehicle) {
        RestTemplate restTemplate = new RestTemplate();
        String userRequest = "http://localhost:8082/vehicles/" + idVehicle;
        VehicleDTO[] response = restTemplate.getForObject(userRequest, VehicleDTO[].class);
        if (response == null) {
            return new ArrayList<>();
        } else {
            return Arrays.asList(response);
        }
    }


    public List<UnavailabilityDTO> requestUnavaibility(int idSoucis) {
        RestTemplate restTemplate = new RestTemplate();
        String userRequest = "http://localhost:8085/unavailability/" + idSoucis;
        UnavailabilityDTO[] response = restTemplate.getForObject(userRequest, UnavailabilityDTO[].class);
        if (response == null) {
            return new ArrayList<>();
        } else {
            return Arrays.asList(response);
        }
    }

    /**
     * Methode pour appeller l'api Vehicle
     *
     * @param idVehicle Id du vehicule demandé
     * @return Retourne la liste de vehicule
     */
    public boolean requestReservation(int idVehicle) {
        RestTemplate restTemplate = new RestTemplate();
        String reservationRequest = "http://localhost:8083/reservations/vehicle/" + idVehicle;
        ReservationDTO[] response = restTemplate.getForObject(reservationRequest, ReservationDTO[].class);
        if (response.length == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean typeVerificationUnavaibility(String typeVehicleUnavailability, String typeVehicleRequest) {
        if (typeVehicleUnavailability.equals(typeVehicleRequest) || typeVehicleUnavailability.contains(typeVehicleRequest)) {
            return true;
        }
        return false;
    }


    public void editMaintenance(Maintenance findindMaintenance, Maintenance maintenanceBodyRequest, MaintenanceDAO maintenanceDAO) {
        findindMaintenance.setIdVehicule(maintenanceBodyRequest.getIdVehicule());
        findindMaintenance.setIdUnavailability(maintenanceBodyRequest.getIdUnavailability());
        maintenanceDAO.save(findindMaintenance);
    }

    public List<Maintenance> getMaintenanceOrThrow(int id, MaintenanceDAO maintenanceDAO) {
        List<Maintenance> maintenance = maintenanceDAO.findById(id);
        if (maintenance == null || maintenance.isEmpty()) {
            throw new MaintenanceNotFind();
        }
        return maintenance;
    }

    public VehicleDTO getVehicleOrThrow(int vehicleId) {
        List<VehicleDTO> list = this.requestVehicle(vehicleId);
        if (list == null || list.isEmpty()) {
            throw new VehicleNotFind();
        }
        return list.getFirst();
    }

    public UnavailabilityDTO getUnavailabilityOrThrow(int unavailabilityId) {
        List<UnavailabilityDTO> list = this.requestUnavaibility(unavailabilityId);
        if (list == null || list.isEmpty()) {
            throw new UnavailabilityNotFind();
        }
        return list.getFirst();
    }
}