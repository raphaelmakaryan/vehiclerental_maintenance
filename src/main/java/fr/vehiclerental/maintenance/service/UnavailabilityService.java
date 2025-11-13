package fr.vehiclerental.maintenance.service;

import fr.vehiclerental.maintenance.entity.Unavailability;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service

public class UnavailabilityService {

    public List<Unavailability> requestUnavaibility(int idUnavailability, UnavaibilityDAO unavaibilityDao) {
        List<Unavailability> response = unavaibilityDao.findById(idUnavailability);
        if (response != null) {
            return response;
        } else {
            return new ArrayList<>();
        }
    }

/*
    /**
     * Methode pour crée une reservation
     *
     * @param reservationsDao Class DAO pour faire les requetes a la bdd
     * @param client          Information du client
     * @param vehicle         Information du vehicule
     * @param informations    Information donnée depuis la requete
     * @param priceFinal      Prix final

    public void createReservation(ReservationsDao reservationsDao, ClientDTO client, VehicleDTO vehicle, Unavailability informations, int priceFinal) {
        Reservations newReservation = new Reservations();
        newReservation.setIdClient(client.getId());
        newReservation.setIdVehicule(vehicle.getId());
        newReservation.setStartReservation(informations.getStartReservation());
        newReservation.setEndReservation(informations.getEndReservation());
        newReservation.setEstimatedKm(informations.getEstimatedKm());
        newReservation.setPriceReservation(priceFinal);
        reservationsDao.save(newReservation);
    }


    /**
     * Methode pour modifier une reservation
     *
     * @param findindReservation     Information de la reservation
     * @param reservationBodyRequest Informations issue de reservation récuperer de la requete
     * @param reservationsDao        Class DAO pour faire les requetes a la bdd

    public void editReservation(Reservations findindReservation, Reservations reservationBodyRequest, ReservationsDao reservationsDao) {
        findindReservation.setIdClient(reservationBodyRequest.getIdClient());
        findindReservation.setIdVehicule(reservationBodyRequest.getIdVehicule());
        findindReservation.setStartReservation(reservationBodyRequest.getStartReservation());
        findindReservation.setEndReservation(reservationBodyRequest.getEndReservation());
        findindReservation.setEstimatedKm(reservationBodyRequest.getEstimatedKm());
        findindReservation.setPriceReservation(reservationBodyRequest.getPriceReservation());
        reservationsDao.save(findindReservation);
    }
    */
}