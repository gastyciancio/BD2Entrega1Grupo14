package ar.edu.unlp.info.bd2.repositories;

import ar.edu.unlp.info.bd2.model.Nurse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NurseRepository extends CrudRepository<Nurse, Long> {

    List<Nurse> findByExperienceGreaterThan(int years);

    @Query("FROM Nurse n WHERE n.id NOT IN (SELECT s.nurse.id FROM Shot s)")
    List<Nurse> getNurseNotShot();

}
