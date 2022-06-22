package ar.edu.unlp.info.bd2.repositories;

import ar.edu.unlp.info.bd2.model.Patient;
import ar.edu.unlp.info.bd2.model.Vaccine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface VaccineRepository extends CrudRepository<Vaccine, Long> {
    @Query("FROM Vaccine v WHERE v.id NOT IN (SELECT s.vaccine.id FROM Shot s)")
    List<Vaccine> getUnappliedVaccines();

    Optional<Vaccine> findByName(String email);
}
