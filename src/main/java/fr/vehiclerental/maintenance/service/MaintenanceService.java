package fr.vehiclerental.maintenance.service;

import fr.vehiclerental.maintenance.entity.*;
import fr.vehiclerental.maintenance.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service

public class MaintenanceService {

    @Autowired
    MaintenanceDAO maintenanceDAO;

    /**
     *
     * @param id
     * @return
     */
    public List<Maintenance> oneMaintenance(int id) {
        try {
            return maintenanceDAO.findById(id);
        } catch (Exception e) {
            throw new MaintenanceNotFind();
        }
    }

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
     */
    public void editMaintenance(Maintenance findindMaintenance, Maintenance maintenanceBodyRequest) {
        findindMaintenance.setidVehicle(maintenanceBodyRequest.getidVehicle());
        findindMaintenance.setIdUnavailability(maintenanceBodyRequest.getIdUnavailability());
        maintenanceDAO.save(findindMaintenance);
    }

    /**
     * Méthode de verification pour récuperer la maintenance
     *
     * @param id Id de la maintenance
     * @return Liste de maintenance ou erreur
     */
    public List<Maintenance> maintenanceVerification(int id) {
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

    /**
     *
     * @param informations
     * @return
     */
    public Map<String, Object> addMaintenanceService(RequiredMaintenance informations) {
        VehicleDTO vehicleDTO = this.vehicleVerification(informations.getId_vehicle());
        UnavailabilityDTO unavailability = this.unavailabilityVerification(informations.getId_unavailability());
        this.requestReservation(informations.getId_vehicle());
        if (this.typeVerificationUnavaibility(unavailability.getTypeVehicle(), vehicleDTO.getType())) {
            Map<String, Object> response = new HashMap<>();
            Maintenance maintenance = new Maintenance();
            maintenance.setidVehicle(vehicleDTO.getId());
            maintenance.setIdUnavailability(unavailability.getId());
            maintenanceDAO.save(maintenance);
            response.put("success", true);
            response.put("message", "Votre maintenance a été ajouté !");
            return response;
        } else {
            throw new VehicleType();
        }
    }

    /**
     *
     * @param idMaintenance
     * @param maintenanceRequest
     * @return
     */
    public Map<String, Object> editMaintenanceService(int idMaintenance, Maintenance maintenanceRequest) {
        try {
            List<Maintenance> maintenance = this.maintenanceVerification(idMaintenance);
            VehicleDTO vehicle = this.vehicleVerification(maintenanceRequest.getidVehicle());
            UnavailabilityDTO unavailability = this.unavailabilityVerification(maintenanceRequest.getIdUnavailability());
            if (!this.typeVerificationUnavaibility(unavailability.getTypeVehicle(), vehicle.getType())) {
                throw new VehicleType();
            } else {
                this.editMaintenance(maintenance.getFirst(), maintenanceRequest);
                Map<String, Object> response = new HashMap<>();
                response.put("success", true);
                response.put("message", "Votre maintenance a été modifié !");
                return response;
            }
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public Map<String, Object> deleteMaintenanceService(int idMaintenance) {
        List<Maintenance> maintenances = maintenanceDAO.findById(idMaintenance);
        if (maintenances == null || maintenances.isEmpty()) {
            throw new MaintenanceNotFind();
        } else {
            maintenanceDAO.delete(maintenances.getFirst());
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Votre maintenance a été supprimé !");
            return response;
        }
    }

    public List<Maintenance> maintenancesWithVehicle(int idVehicle) {
        try {
            return maintenanceDAO.findByIdVehicle(idVehicle);
        } catch (Exception e) {
            throw new MaintenanceNotFind();
        }
    }

}