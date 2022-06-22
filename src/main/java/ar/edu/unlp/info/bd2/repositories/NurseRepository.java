package ar.edu.unlp.info.bd2.repositories;

import ar.edu.unlp.info.bd2.model.Nurse;
import ar.edu.unlp.info.bd2.model.Patient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NurseRepository extends CrudRepository<Nurse, Long> {

    @Query("FROM Nurse n where n.experience > ?1")
    List<Nurse> getNurseWithMoreThanNYearsExperience(int years);

    @Query("FROM Nurse n WHERE n.id NOT IN (SELECT s.nurse.id FROM Shot s)")
    List<Nurse> getNurseNotShot();

}
