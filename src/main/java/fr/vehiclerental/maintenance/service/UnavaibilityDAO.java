package fr.vehiclerental.maintenance.service;

import fr.vehiclerental.maintenance.entity.Unavailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnavaibilityDAO extends JpaRepository<Unavailability, Integer> {
    List<Unavailability> findById(int id);


    List<Unavailability> findAll();

    void delete(Unavailability client);

    Unavailability save(Unavailability client);
}