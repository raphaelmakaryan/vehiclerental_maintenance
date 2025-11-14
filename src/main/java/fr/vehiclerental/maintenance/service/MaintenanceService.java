package fr.vehiclerental.maintenance.service;

import fr.vehiclerental.maintenance.entity.*;
import fr.vehiclerental.maintenance.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

@Slf4j
@Service

public class MaintenanceService {

    /**
     * Methode qui appeller l'api Vehicle
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

    /**
     * Methode qui appellera l'api Unavaibility
     *
     * @param idSoucis Id du soucis
     * @return Liste ou vide
     */
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
     * Methode qui appellera l'api Reservation via l'id du vehicule
     *
     * @param idVehicle Id du vehicule demandé
     * @return Vrai ou erreur
     */
    public boolean requestReservation(int idVehicle) {
        RestTemplate restTemplate = new RestTemplate();
        String reservationRequest = "http://localhost:8083/reservations/vehicle/" + idVehicle;
        ReservationDTO[] response = restTemplate.getForObject(reservationRequest, ReservationDTO[].class);
        if (response.length == 0) {
            return true;
        } else {
            throw new VehicleAlreadyReserved();
        }
    }

    /**
     * Méthode de vérification si le type du véhicule selon le soucis est cohérent
     *
     * @param typeVehicleUnavailability Type du véhicule du soucis choppé via l'api de unavailability
     * @param typeVehicleRequest        Type de véhicule choppé via l'api de vehicle
     * @return Vrai ou erreur
     */
    public boolean typeVerificationUnavaibility(String typeVehicleUnavailability, String typeVehicleRequest) {
        if (typeVehicleUnavailability.equals(typeVehicleRequest) || typeVehicleUnavailability.contains(typeVehicleRequest)) {
            return true;
        } else {
            throw new VehicleType();
        }
    }


    /**
     * Méthode pour modifié un entretien
     *
     * @param findindMaintenance     Maintenance trouvé via son id
     * @param maintenanceBodyRequest Information de la maintenance via a requete
     * @param maintenanceDAO         DAO de maintenance
     */
    public void editMaintenance(Maintenance findindMaintenance, Maintenance maintenanceBodyRequest, MaintenanceDAO maintenanceDAO) {
        findindMaintenance.setIdVehicule(maintenanceBodyRequest.getIdVehicule());
        findindMaintenance.setIdUnavailability(maintenanceBodyRequest.getIdUnavailability());
        maintenanceDAO.save(findindMaintenance);
    }

    /**
     * Méthode de verification pour récuperer la maintenance
     *
     * @param id             Id de la maintenance
     * @param maintenanceDAO DAO maintenance
     * @return Liste de maintenance ou erreur
     */
    public List<Maintenance> maintenanceVerification(int id, MaintenanceDAO maintenanceDAO) {
        List<Maintenance> maintenance = maintenanceDAO.findById(id);
        if (maintenance == null || maintenance.isEmpty()) {
            throw new MaintenanceNotFind();
        }
        return maintenance;
    }

    /**
     * Méthode de verification pour récuperer le vehicle en appeleant l'api Vehicle
     *
     * @param vehicleId Id du vehicle
     * @return Liste du vehicule ou erreur
     */
    public VehicleDTO vehicleVerification(int vehicleId) {
        List<VehicleDTO> list = this.requestVehicle(vehicleId);
        if (list == null || list.isEmpty()) {
            throw new VehicleNotFind();
        }
        return list.getFirst();
    }

    /**
     * Méthode de verification pour récuperer le soucis en appeleant l'api unavailability
     *
     * @param unavailabilityId Id du unavailability
     * @return Liste de unavailability ou erreur
     */
    public UnavailabilityDTO unavailabilityVerification(int unavailabilityId) {
        List<UnavailabilityDTO> list = this.requestUnavaibility(unavailabilityId);
        if (list == null || list.isEmpty()) {
            throw new UnavailabilityNotFind();
        }
        return list.getFirst();
    }


}