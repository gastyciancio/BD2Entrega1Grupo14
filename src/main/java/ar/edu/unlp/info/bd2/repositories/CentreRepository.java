package ar.edu.unlp.info.bd2.repositories;

import ar.edu.unlp.info.bd2.model.Centre;
import ar.edu.unlp.info.bd2.model.Vaccine;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import java.util.List;
import java.util.Optional;

public interface CentreRepository extends CrudRepository<Centre, Long> {

    @Query("FROM Centre order by staff.size DESC")
    List<Centre> getCentresTopNStaff();

    @Query("SELECT s.centre FROM Shot as s GROUP BY s.centre ORDER BY COUNT(*) DESC")
    Centre getTopShotCentre();

    Optional<Centre> findByName(String email);


}
