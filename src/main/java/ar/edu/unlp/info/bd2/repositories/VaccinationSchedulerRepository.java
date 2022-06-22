package ar.edu.unlp.info.bd2.repositories;

import ar.edu.unlp.info.bd2.model.VaccinationSchedule;
import ar.edu.unlp.info.bd2.model.Vaccine;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VaccinationSchedulerRepository extends CrudRepository<VaccinationSchedule, Long> {

}
